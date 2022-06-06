package es.unizar.tmdad.controller;

import es.unizar.tmdad.dto.UserCreationDto;
import es.unizar.tmdad.dto.UserDto;
import es.unizar.tmdad.exception.UnauthorizedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

public interface UserController {

    @Operation(description = "Create a new user",
            parameters = {
                    @Parameter(name = "X-Auth-Firebase", required = true, in = ParameterIn.HEADER, description = "Authentication token (JWT)"),
                    @Parameter(name = "X-Auth-User", required = true, in = ParameterIn.HEADER, description = "Email of the user the X-Auth-Firebase token belongs to.")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was created successfully.")
    })
    UserDto createUser(@Parameter(description = "Email of the user, must match X-Auth-User header", example = "example3@whoarethey.me") String email, UserCreationDto userDto, String authEmail);

    @Operation(description = "Delete an existing user",
            parameters = {
                    @Parameter(name = "X-Auth-Firebase", required = true, in = ParameterIn.HEADER, description = "Authentication token (JWT)"),
                    @Parameter(name = "X-Auth-User", required = true, in = ParameterIn.HEADER, description = "Email of the user the X-Auth-Firebase token belongs to.")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was deleted successfully.")
    })
    void deleteUser(@Parameter(description = "Email of the user, must match X-Auth-User header", example = "example3@whoarethey.me") String name, String authEmail);

    @Operation(description = "Get an existing user",
            parameters = {
                    @Parameter(name = "X-Auth-Firebase", required = true, in = ParameterIn.HEADER, description = "Authentication token (JWT)"),
                    @Parameter(name = "X-Auth-User", required = true, in = ParameterIn.HEADER, description = "Email of the user the X-Auth-Firebase token belongs to.")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user was found and retrieved.")
    })
    UserDto getUser(@Parameter(description = "Email of the user, must match X-Auth-User header", example = "example3@whoarethey.me") String name, String authEmail);

    @Operation(description = "Create a new conversation with a user",
            parameters = {
                    @Parameter(name = "X-Auth-Firebase", required = true, in = ParameterIn.HEADER, description = "Authentication token (JWT)"),
                    @Parameter(name = "X-Auth-User", required = true, in = ParameterIn.HEADER, description = "Email of the user the X-Auth-Firebase token belongs to.")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The new conversation was created.")
    })
    UserDto addContact(@Parameter(description = "Email of the user, must match X-Auth-User header", example = "example3@whoarethey.me") String email,
                       @Parameter(description = "Email of the new contact", example = "example3@whoarethey.me") String contact, String authEmail);

    @Operation(description = "Get the conversations of a user",
            parameters = {
                    @Parameter(name = "X-Auth-Firebase", required = true, in = ParameterIn.HEADER, description = "Authentication token (JWT)"),
                    @Parameter(name = "X-Auth-User", required = true, in = ParameterIn.HEADER, description = "Email of the user the X-Auth-Firebase token belongs to.")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The conversations are found and retrieved.")
    })
    Set<UserDto> getContacts(@Parameter(description = "Email of the user, must match X-Auth-User header", example = "example3@whoarethey.me") String email, String authEmail);

    @GetMapping("/{email}/superuser")
    @Operation(description = "Set the superuser flag of a user",
            parameters = {
                    @Parameter(name = "X-Auth-Firebase", required = true, in = ParameterIn.HEADER, description = "Authentication token (JWT)"),
                    @Parameter(name = "X-Auth-User", required = true, in = ParameterIn.HEADER, description = "Email of the user the X-Auth-Firebase token belongs to.")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The flag has been updated.")
    })
    UserDto makeSuperUser(@Parameter(description = "Email of the user, must not match X-Auth-User header", example = "example3@whoarethey.me") @PathVariable String email,
                          @Parameter(description = "Boolean stating whether this user must or mustn't be a superuser") @RequestParam("superuser") Boolean makeSuperuser,
                          @RequestHeader("X-Auth-User") String authEmail) throws UnauthorizedException;
}
