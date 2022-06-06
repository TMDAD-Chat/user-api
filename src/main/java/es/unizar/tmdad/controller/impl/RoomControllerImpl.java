package es.unizar.tmdad.controller.impl;

import es.unizar.tmdad.controller.RoomController;
import es.unizar.tmdad.dto.RoomCreationDto;
import es.unizar.tmdad.dto.RoomDto;
import es.unizar.tmdad.exception.UnauthorizedException;
import es.unizar.tmdad.service.RoomService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomControllerImpl implements RoomController {

    private final RoomService roomService;

    public RoomControllerImpl(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    @PostMapping("/")
    public RoomDto createRoom(@RequestBody RoomCreationDto dto, @RequestHeader("X-Auth-User") String owner) {
        return this.roomService.addRoom(dto, owner);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable("id") Long roomId, @RequestHeader("X-Auth-User") String owner) throws UnauthorizedException {
        this.roomService.deleteRoom(roomId, owner);
    }

    @Override
    @GetMapping("/{id}")
    public RoomDto getRoom(@PathVariable("id") Long roomId) {
        return this.roomService.getRoom(roomId);
    }

    @Override
    @PutMapping("/{roomId}/user/{userId}")
    public RoomDto addUserToRoom(@PathVariable("roomId") Long roomId, @PathVariable("userId") String userId, @RequestHeader("X-Auth-User") String owner) {
        return this.roomService.modifyUserInRoom(roomId, userId, owner, RoomService.Operation.ADD);
    }

    @Override
    @DeleteMapping("/{roomId}/user/{userId}")
    public RoomDto removeUserFromRoom(@PathVariable("roomId") Long roomId, @PathVariable("userId") String userId, @RequestHeader("X-Auth-User") String owner) {
        return this.roomService.modifyUserInRoom(roomId, userId, owner, RoomService.Operation.REMOVE);
    }

    @Override
    @GetMapping("/list")
    public List<RoomDto> getRoomList(@RequestHeader("X-Auth-User") String userEmail) {
        return this.roomService.getUserRooms(userEmail);
    }
}
