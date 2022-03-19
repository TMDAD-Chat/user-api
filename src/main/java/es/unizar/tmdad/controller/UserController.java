package es.unizar.tmdad.controller;

import es.unizar.tmdad.dto.*;
import es.unizar.tmdad.dto.UserCreationDto;

public interface UserController {

    UserDto createUser(UserCreationDto dto);
    void deleteUser(String userId);
    UserDto getUser(String userId);

}
