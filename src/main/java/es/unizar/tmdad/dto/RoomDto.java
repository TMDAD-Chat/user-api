package es.unizar.tmdad.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoomDto {

    private Long id;
    private String name;
    private String owner;
    private List<String> users;

}
