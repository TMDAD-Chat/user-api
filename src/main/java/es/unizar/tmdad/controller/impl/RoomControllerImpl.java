package es.unizar.tmdad.controller.impl;

import es.unizar.tmdad.controller.RoomController;
import es.unizar.tmdad.dto.RoomCreationDto;
import es.unizar.tmdad.dto.RoomDto;
import es.unizar.tmdad.service.RoomService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/room")
public class RoomControllerImpl implements RoomController {

    private final RoomService roomService;

    public RoomControllerImpl(RoomService roomService) {
        this.roomService = roomService;
    }

    @Override
    @PostMapping("/{owner}")
    public RoomDto createRoom(@RequestBody RoomCreationDto dto, @PathVariable("owner") String owner) {
        return this.roomService.addRoom(dto, owner);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable("id") Long roomId) {
        this.roomService.deleteRoom(roomId);
    }

    @Override
    @GetMapping("/{id}")
    public RoomDto getRoom(@PathVariable("id") Long roomId) {
        return this.roomService.getRoom(roomId);
    }

    @Override
    @PutMapping("/{roomId}/user/{userId}")
    public RoomDto addUserToRoom(@PathVariable("roomId") Long roomId, @PathVariable("userId") String userId, @RequestParam("owner") String owner) {
        return this.roomService.modifyUserInRoom(roomId, userId, owner, RoomService.Operation.ADD);
    }

    @Override
    @DeleteMapping("/{roomId}/user/{userId}")
    public RoomDto removeUserFromRoom(@PathVariable("roomId") Long roomId, @PathVariable("userId") String userId, @RequestParam("owner") String owner) {
        return this.roomService.modifyUserInRoom(roomId, userId, owner, RoomService.Operation.REMOVE);
    }
}
