package es.unizar.tmdad.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Object represent a room creation request")
public class RoomCreationDto {

    @Schema(description = "Room name, cannot be changed", example = "Trabajo de TMDAD")
    private String roomName;

}
