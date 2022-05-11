package es.unizar.tmdad.controller.impl;

import es.unizar.tmdad.controller.UserController;
import es.unizar.tmdad.dto.UserCreationDto;
import es.unizar.tmdad.dto.UserDto;
import es.unizar.tmdad.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PutMapping(value = "/{email}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto createUser(@PathVariable String email, @RequestBody UserCreationDto userDto) {
        return userService.addUser(userDto);
    }

    @Override
    @DeleteMapping(value = "/{email}")
    public void deleteUser(@PathVariable String email) {
        userService.deleteUser(email);
    }

    @Override
    @GetMapping("/{name}")
    public UserDto getUser(@PathVariable("name") String name) {
        return userService.getUser(name);
    }

}
