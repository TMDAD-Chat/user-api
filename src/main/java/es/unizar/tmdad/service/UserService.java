package es.unizar.tmdad.service;

import es.unizar.tmdad.dto.*;
import es.unizar.tmdad.dto.UserCreationDto;
import es.unizar.tmdad.repository.entity.UserEntity;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    UserDto addUser(UserCreationDto userDto);
    UserDto getUser(String id);
    Optional<UserEntity> getUserEntity(String id);
    void deleteUser(String id);
    UserDto addContact(String email, String contact);
    Set<UserDto> getContacts(String email);
}
