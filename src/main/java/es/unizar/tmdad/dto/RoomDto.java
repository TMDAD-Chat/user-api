package es.unizar.tmdad.dto;

import lombok.*;

@Data
@Builder
public class RoomDto {

    private String id;
    private String userName;
    private Boolean isSuperUser;

}
