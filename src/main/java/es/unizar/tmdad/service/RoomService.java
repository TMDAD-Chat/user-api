package es.unizar.tmdad.service;

import es.unizar.tmdad.dto.RoomCreationDto;
import es.unizar.tmdad.dto.RoomDto;
import es.unizar.tmdad.exception.UnauthorizedException;

public interface RoomService {

    RoomDto addRoom(RoomCreationDto userDto, String owner);
    RoomDto getRoom(Long id);
    void deleteRoom(Long id, String owner) throws UnauthorizedException;

    RoomDto modifyUserInRoom(Long roomId, String userId, String owner, Operation op);

    enum Operation {
        ADD, REMOVE
    }
}
