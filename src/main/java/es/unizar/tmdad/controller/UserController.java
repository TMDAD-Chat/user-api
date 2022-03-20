package es.unizar.tmdad.controller;

import es.unizar.tmdad.dto.*;
import es.unizar.tmdad.dto.UserCreationDto;

public interface UserController {

    UserDto createUser(String name, UserCreationDto dto);
    void deleteUser(String name);
    UserDto getUser(String name);

}
