package es.unizar.tmdad.service;

import es.unizar.tmdad.dto.*;
import es.unizar.tmdad.dto.UserCreationDto;

public interface UserService {

    UserDto addUser(UserCreationDto userDto);
    UserDto getUser(String id);
    void deleteUser(String id);

}
