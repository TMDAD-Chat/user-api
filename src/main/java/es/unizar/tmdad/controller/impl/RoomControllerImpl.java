package es.unizar.tmdad.controller.impl;

import es.unizar.tmdad.controller.RoomController;
import es.unizar.tmdad.dto.RoomCreationDto;
import es.unizar.tmdad.dto.RoomDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomControllerImpl implements RoomController {

    @Override
    @PostMapping
    public ResponseEntity<RoomDto> createRoom(RoomCreationDto dto) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable("id") String roomId) {

    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getRoom(@PathVariable("id") String roomId) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
