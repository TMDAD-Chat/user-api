package es.unizar.tmdad.service;

import es.unizar.tmdad.dto.*;
import es.unizar.tmdad.dto.UserCreationDto;
import es.unizar.tmdad.repository.entity.UserEntity;

import java.util.Optional;

public interface UserService {

    UserDto addUser(UserCreationDto userDto);
    UserDto getUser(String id);
    Optional<UserEntity> getUserEntity(String id);
    void deleteUser(String id);

}
