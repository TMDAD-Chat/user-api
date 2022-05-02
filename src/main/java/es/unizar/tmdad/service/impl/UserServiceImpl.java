package es.unizar.tmdad.service.impl;

import es.unizar.tmdad.adt.EventType;
import es.unizar.tmdad.adt.UserEvent;
import es.unizar.tmdad.dto.*;
import es.unizar.tmdad.dto.UserCreationDto;
import es.unizar.tmdad.mapper.UserMapper;
import es.unizar.tmdad.repository.UserRepository;
import es.unizar.tmdad.repository.entity.UserEntity;
import es.unizar.tmdad.service.RabbitService;
import es.unizar.tmdad.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final RabbitService rabbitService;

    public UserServiceImpl(UserRepository repository, UserMapper mapper, RabbitService rabbitService) {
        this.repository = repository;
        this.mapper = mapper;
        this.rabbitService = rabbitService;
    }

    @Override
    public UserDto addUser(UserCreationDto userDto) {
        UserEntity entity = mapper.mapUser(userDto);
        entity = repository.save(entity);

        UserEvent event = UserEvent.builder()
                .subject(entity.getEmail())
                .event(EventType.ADD_USER)
                .build();
        rabbitService.sendEvent(event);

        return mapper.mapUser(entity);
    }

    @Override
    public UserDto getUser(String name) {
        return repository.findById(name)
                .map(mapper::mapUser)
                .orElseThrow(() -> new RuntimeException("NOT FOUND: Error getting user with name " + name + "."));
    }

    @Override
    public Optional<UserEntity> getUserEntity(String id) {
        return repository.findById(id);
    }

    @Override
    public void deleteUser(String name) {
        repository.deleteById(name);

        UserEvent event = UserEvent.builder()
                .subject(name)
                .event(EventType.DELETE_USER)
                .build();
        rabbitService.sendEvent(event);

    }
}
