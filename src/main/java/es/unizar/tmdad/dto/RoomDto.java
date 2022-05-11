package es.unizar.tmdad.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoomDto {

    private Long id;
    private String name;
    private UserDto owner;
    private List<UserDto> users;

}
