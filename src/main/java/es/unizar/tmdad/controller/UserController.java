package es.unizar.tmdad.controller;

import es.unizar.tmdad.dto.*;
import es.unizar.tmdad.dto.UserCreationDto;

import java.util.Set;

public interface UserController {

    UserDto createUser(String name, UserCreationDto dto);
    void deleteUser(String name);
    UserDto getUser(String name);
    UserDto addContact(String email, String contact);
    Set<UserDto> getContacts(String email);
}
