package es.unizar.tmdad.service.impl;

import es.unizar.tmdad.adt.EventType;
import es.unizar.tmdad.adt.UserEvent;
import es.unizar.tmdad.dto.RoomCreationDto;
import es.unizar.tmdad.dto.RoomDto;
import es.unizar.tmdad.exception.EntityNotFoundException;
import es.unizar.tmdad.exception.UnauthorizedException;
import es.unizar.tmdad.exception.UserNotTheOwnerException;
import es.unizar.tmdad.mapper.RoomMapper;
import es.unizar.tmdad.repository.RoomRepository;
import es.unizar.tmdad.repository.entity.RoomEntity;
import es.unizar.tmdad.repository.entity.UserEntity;
import es.unizar.tmdad.service.RabbitService;
import es.unizar.tmdad.service.RoomService;
import es.unizar.tmdad.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository repository;
    private final UserService userService;
    private final RoomMapper mapper;
    private final RabbitService rabbitService;

    public RoomServiceImpl(RoomRepository repository, UserService userService, RoomMapper mapper, RabbitService rabbitService) {
        this.repository = repository;
        this.userService = userService;
        this.mapper = mapper;
        this.rabbitService = rabbitService;
    }

    @Override
    public RoomDto addRoom(RoomCreationDto userDto, String owner) {
        Optional<UserEntity> ownerEntity = userService.getUserEntity(owner);

        RoomEntity entity = mapper.mapRoom(userDto, ownerEntity.orElseThrow());
        entity = repository.save(entity);

        UserEvent event = UserEvent.builder()
                .subject(String.valueOf(entity.getId()))
                .event(EventType.ADD_ROOM)
                .argument(owner)
                .build();
        rabbitService.sendEvent(event);

        return mapper.mapRoom(entity);
    }

    @Override
    public RoomDto getRoom(Long id) {
        return repository.findById(id)
                .map(mapper::mapRoom)
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.EntityType.ROOM, "NOT FOUND: Error getting room with id " + id + "."));
    }

    @Override
    public void deleteRoom(Long id, String owner) throws UnauthorizedException {
        var room = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.EntityType.ROOM, String.valueOf(id)));
        if(!Objects.equals(owner, room.getOwner().getEmail())){
            throw new UnauthorizedException("User ("+owner+") is not the owner of the room, so he cannot delete it.");
        }
        repository.deleteById(id);

        UserEvent event = UserEvent.builder()
                .subject(String.valueOf(id))
                .event(EventType.DELETE_ROOM)
                .build();
        rabbitService.sendEvent(event);

    }

    @Override
    public RoomDto modifyUserInRoom(Long roomId, String userId, String ownerName, Operation op) {
        RoomEntity room = this.repository.findById(roomId).orElseThrow();
        UserEntity owner = this.userService.getUserEntity(ownerName).orElseThrow();
        UserEntity user = this.userService.getUserEntity(userId).orElseThrow();

        if(!Objects.equals(room.getOwner(), owner)){
            throw new UserNotTheOwnerException("Cannot add user to room if not the owner.");
        }

        switch (op){
            case ADD:
                //This add operation is already idempotent, so no contains check needed.
                room.getUsers().add(user);
                break;
            case REMOVE:
                room.getUsers().remove(user);
                break;
        }

        this.repository.save(room);

        UserEvent event = UserEvent.builder()
                .subject(String.valueOf(roomId))
                .argument(userId)
                .event(Objects.equals(Operation.ADD, op) ? EventType.ADD_USER_TO_ROOM : EventType.REMOVE_USER_FROM_ROOM)
                .build();
        rabbitService.sendEvent(event);

        return this.mapper.mapRoom(room);
    }
}
