package es.unizar.tmdad.controller.impl;

import es.unizar.tmdad.controller.UserController;
import es.unizar.tmdad.dto.UserCreationDto;
import es.unizar.tmdad.dto.UserDto;
import es.unizar.tmdad.exception.AuthUserMismatchException;
import es.unizar.tmdad.exception.UnauthorizedException;
import es.unizar.tmdad.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(value = "/user")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PutMapping(value = "/{email}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserDto createUser(@PathVariable String email, @RequestBody UserCreationDto userDto, @RequestHeader("X-Auth-User") String authEmail) {
        if(!Objects.equals(email, authEmail)){
            throw new AuthUserMismatchException(email, authEmail);
        }else if(!Objects.equals(authEmail, userDto.getEmail())){
            throw new AuthUserMismatchException(authEmail, userDto.getEmail());
        }
        return userService.addUser(userDto);
    }

    @Override
    @DeleteMapping(value = "/{email}")
    public void deleteUser(@PathVariable String email, @RequestHeader("X-Auth-User") String authEmail) {
        if(!Objects.equals(email, authEmail)){
            throw new AuthUserMismatchException(email, authEmail);
        }
        userService.deleteUser(email);
    }

    @Override
    @GetMapping("/{email}")
    public UserDto getUser(@PathVariable("email") String email, @RequestHeader("X-Auth-User") String authEmail) {
        return userService.getUser(email);
    }

    @Override
    @PutMapping("/{email}/contacts/{contact}")
    public UserDto addContact(@PathVariable("email") String email, @PathVariable("contact") String contact, @RequestHeader("X-Auth-User") String authEmail) {
        if(!Objects.equals(email, authEmail)){
            throw new AuthUserMismatchException(email, authEmail);
        }
        return userService.addContact(email, contact);
    }

    @Override
    @GetMapping("/{email}/contacts")
    public Set<UserDto> getContacts(@PathVariable("email") String email, @RequestHeader("X-Auth-User") String authEmail) {
        if(!Objects.equals(email, authEmail)){
            throw new AuthUserMismatchException(email, authEmail);
        }
        return userService.getContacts(email);
    }

    @Override
    @PutMapping("/{email}/superuser")
    public UserDto makeSuperUser(@PathVariable String email, @RequestParam("superuser") Boolean makeSuperuser, @RequestHeader("X-Auth-User") String authEmail) throws UnauthorizedException {
        var requester = userService.getUser(authEmail);
        if(!requester.getIsSuperUser()){
            throw new UnauthorizedException("User " + authEmail + " is not authorised to update superuser flag.");
        }

        return userService.updateSuperuserFlagOf(email, makeSuperuser);
    }
}
