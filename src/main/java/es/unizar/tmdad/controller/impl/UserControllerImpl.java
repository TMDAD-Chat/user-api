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
    @PostMapping
    public UserDto createUser(@RequestBody UserCreationDto userDto) {
        return userService.addUser(userDto);
    }

    @Override
    @DeleteMapping
    public void deleteUser(String userId) {
        userService.deleteUser(userId);
    }

    @Override
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") String userId) {
        return userService.getUser(userId);
    }

}
