package es.unizar.tmdad.service;

import es.unizar.tmdad.dto.RoomCreationDto;
import es.unizar.tmdad.dto.RoomDto;

import java.util.List;

public interface RoomService {

    RoomDto addRoom(RoomCreationDto userDto, String owner);
    RoomDto getRoom(Long id);
    List<RoomDto> getUserRooms(String email);
    void deleteRoom(Long id);

    RoomDto modifyUserInRoom(Long roomId, String userId, String owner, Operation op);

    enum Operation {
        ADD, REMOVE
    }
}
