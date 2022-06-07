package es.unizar.tmdad.controller;

import es.unizar.tmdad.dto.RoomCreationDto;
import es.unizar.tmdad.dto.RoomDto;
import es.unizar.tmdad.exception.UnauthorizedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

public interface RoomController {

    @Operation(description = "Create a new room",
            parameters = {
                    @Parameter(name = "X-Auth-Firebase", required = true, in = ParameterIn.HEADER, description = "Authentication token (JWT)"),
                    @Parameter(name = "X-Auth-User", required = true, in = ParameterIn.HEADER, description = "Email of the user the X-Auth-Firebase token belongs to.")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room was created successfully.")
    })
    RoomDto createRoom(RoomCreationDto dto, String owner);

    @Operation(description = "Delete an existing room",
            parameters = {
                    @Parameter(name = "X-Auth-Firebase", required = true, in = ParameterIn.HEADER, description = "Authentication token (JWT)"),
                    @Parameter(name = "X-Auth-User", required = true, in = ParameterIn.HEADER, description = "Email of the user the X-Auth-Firebase token belongs to.")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room was deleted successfully.")
    })
    @DeleteMapping("/{id}")
    void deleteRoom(@Parameter(description = "Room to be deleted", example = "1") @PathVariable("id") Long roomId, @RequestHeader("X-Auth-User") String owner) throws UnauthorizedException;

    @Operation(description = "Get an existing room",
            parameters = {
                    @Parameter(name = "X-Auth-Firebase", required = true, in = ParameterIn.HEADER, description = "Authentication token (JWT)"),
                    @Parameter(name = "X-Auth-User", required = true, in = ParameterIn.HEADER, description = "Email of the user the X-Auth-Firebase token belongs to.")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Room was found and retrieved.")
    })
    RoomDto getRoom(@Parameter(description = "Room to be retrieved", example = "1")Long roomId);

    @Operation(description = "Add a user to a room, if the caller is its owner",
            parameters = {
                    @Parameter(name = "X-Auth-Firebase", required = true, in = ParameterIn.HEADER, description = "Authentication token (JWT)"),
                    @Parameter(name = "X-Auth-User", required = true, in = ParameterIn.HEADER, description = "Email of the user the X-Auth-Firebase token belongs to.")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was added to the room")
    })
    RoomDto addUserToRoom(@Parameter(description = "Room the user needs to be added to", example = "1") Long roomId,
                          @Parameter(description = "User to add to the room") String user, String owner);

    @Operation(description = "Remove a user from a room, if the caller is its owner",
            parameters = {
                    @Parameter(name = "X-Auth-Firebase", required = true, in = ParameterIn.HEADER, description = "Authentication token (JWT)"),
                    @Parameter(name = "X-Auth-User", required = true, in = ParameterIn.HEADER, description = "Email of the user the X-Auth-Firebase token belongs to.")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User was removed from the room")
    })
    RoomDto removeUserFromRoom(@Parameter(description = "Room the user needs to be removed from", example = "1") Long roomId,
                               @Parameter(description = "User to remove from the room") String user, String owner);

    @Operation(description = "Get all rooms this user is in",
            parameters = {
                    @Parameter(name = "X-Auth-Firebase", required = true, in = ParameterIn.HEADER, description = "Authentication token (JWT)"),
                    @Parameter(name = "X-Auth-User", required = true, in = ParameterIn.HEADER, description = "Email of the user the X-Auth-Firebase token belongs to.")
            })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rooms of the user were retrieved successfully")
    })
    List<RoomDto> getRoomList(String userEmail);
}
