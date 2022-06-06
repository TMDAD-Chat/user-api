package es.unizar.tmdad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Object representing a user")
public class UserDto {

    @Schema(description = "User name", example = "Juan")
    private String name;
    @Schema(description = "Flag to state whether this user has special privileges or not.", example = "true")
    private Boolean isSuperUser;
    @Schema(description = "User's email", example = "example@whoisher.me")
    private String email;

    @Schema(description = "Image to show near the user name, as it is they profile picture.", example = "https://example.com/example/image.png")
    private String photoUri;

}
