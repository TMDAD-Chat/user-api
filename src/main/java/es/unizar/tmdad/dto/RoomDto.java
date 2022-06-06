package es.unizar.tmdad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(description = "Object representing a room")
public class RoomDto {

    @Schema(description = "Unique identifier of this room", example = "1")
    private Long id;
    @Schema(description = "Name of this room", example = "Trabajo de TMDAD")
    private String name;
    @Schema(description = "Owner of this room, whom will be able to perform specific actions, such as delete the room.")
    private UserDto owner;

    @Schema(description = "All users who are inside the room and thus, can send a message, connect, etc... The owner of this room is not included in this collections")
    private List<UserDto> users;

}
