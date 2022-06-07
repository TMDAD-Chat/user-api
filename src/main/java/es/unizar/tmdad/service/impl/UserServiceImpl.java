package es.unizar.tmdad.service.impl;

import es.unizar.tmdad.adt.EventType;
import es.unizar.tmdad.adt.UserEvent;
import es.unizar.tmdad.dto.*;
import es.unizar.tmdad.dto.UserCreationDto;
import es.unizar.tmdad.exception.EntityNotFoundException;
import es.unizar.tmdad.mapper.UserMapper;
import es.unizar.tmdad.repository.UserRepository;
import es.unizar.tmdad.repository.entity.UserEntity;
import es.unizar.tmdad.service.RabbitService;
import es.unizar.tmdad.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
        UserEntity user = this.getUserEntity(userDto.getEmail()).orElse(null);
        if(user != null) return mapper.mapUser(user);

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
                .orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.EntityType.USER, "NOT FOUND: Error getting user with name " + name + "."));
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

    @Override
    public UserDto addContact(String email, String contact) {
        UserEntity owner = this.getUserEntity(email).orElseThrow();
        UserEntity newContact = this.getUserEntity(contact).orElseThrow();

        owner.getContacts().add(newContact);
        newContact.getContacts().add(owner);

        this.repository.save(owner);

        return mapper.mapUser(newContact);
    }

    @Override
    public Set<UserDto> getContacts(String email) {
        var contacts = repository.findById(email)
                .map(UserEntity::getContacts)
                .orElseThrow(() -> new RuntimeException("NOT FOUND: Error getting user with name " + email + "."));

        return contacts.stream()
                .map(mapper::mapUser).collect(Collectors.toSet());
    }

    @Override
    public UserDto updateSuperuserFlagOf(String email, Boolean makeSuperuser) {
        var user = repository.findById(email).orElseThrow(() -> new EntityNotFoundException(EntityNotFoundException.EntityType.USER, email));
        if(!user.getIsSuperUser().equals(makeSuperuser)) {
            user.setIsSuperUser(makeSuperuser);
            user = repository.save(user);

            UserEvent event = UserEvent.builder()
                    .subject(email)
                    .event(EventType.UPDATE_USER_SU_FLAG)
                    .argument(String.valueOf(makeSuperuser))
                    .build();
            rabbitService.sendEvent(event);
        }

        return mapper.mapUser(user);
    }
}
