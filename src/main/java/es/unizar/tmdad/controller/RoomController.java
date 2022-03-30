package es.unizar.tmdad.controller;

import es.unizar.tmdad.dto.RoomCreationDto;
import es.unizar.tmdad.dto.RoomDto;
import org.springframework.http.ResponseEntity;

public interface RoomController {

    ResponseEntity<RoomDto> createRoom(RoomCreationDto dto);
    void deleteRoom(String userId);
    ResponseEntity<RoomDto> getRoom(String userId);

}
