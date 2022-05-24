package es.unizar.tmdad.controller;

import es.unizar.tmdad.dto.RoomCreationDto;
import es.unizar.tmdad.dto.RoomDto;

import java.util.List;

public interface RoomController {

    RoomDto createRoom(RoomCreationDto dto, String owner);
    void deleteRoom(Long roomId);
    RoomDto getRoom(Long roomId);
    RoomDto addUserToRoom(Long roomId, String user, String owner);
    RoomDto removeUserFromRoom(Long roomId, String user, String owner);
    List<RoomDto> getRoomList(String userEmail);
}
