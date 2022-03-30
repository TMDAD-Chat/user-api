package es.unizar.tmdad.controller.impl;

import es.unizar.tmdad.controller.UserController;
import es.unizar.tmdad.dto.*;
import es.unizar.tmdad.dto.UserCreationDto;
import es.unizar.tmdad.service.UserService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", consumes = {MediaType.APPLICATION_JSON_VALUE})
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PutMapping("/{name}")
    public UserDto createUser(@PathVariable String name, @RequestBody UserCreationDto userDto) {
        userDto.setUserName(name);
        return userService.addUser(userDto);
    }

    @Override
    @DeleteMapping
    public void deleteUser(String name) {
        userService.deleteUser(name);
    }

    @Override
    @GetMapping("/{name}")
    public UserDto getUser(@PathVariable("name") String name) {
        return userService.getUser(name);
    }

}
