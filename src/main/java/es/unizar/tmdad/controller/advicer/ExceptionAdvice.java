package es.unizar.tmdad.controller.advicer;

import es.unizar.tmdad.exception.AuthUserMismatchException;
import es.unizar.tmdad.exception.EntityNotFoundException;
import es.unizar.tmdad.exception.UnauthorizedException;
import es.unizar.tmdad.exception.UserNotTheOwnerException;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(value = {AuthUserMismatchException.class})
    @ApiResponse(description = "Auth headers do not match, check all values are provided", responseCode = "400")
    public ResponseEntity<Void> handleAuthMismatch(AuthUserMismatchException ignoredException){
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ApiResponse(description = "Entity could not be found, or user is not in this room", responseCode = "404")
    public ResponseEntity<Void> handleEntityNotFound(EntityNotFoundException ignoredException){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {UserNotTheOwnerException.class})
    @ApiResponse(description = "User is not the owner of the room", responseCode = "400")
    public ResponseEntity<Void> handleEntityNotFound(UserNotTheOwnerException ignoredException){
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    @ApiResponse(description = "User is not allowed to call this endpoint, check they have the proper permissions.", responseCode = "401")
    public ResponseEntity<Void> handleUnauthorised(UnauthorizedException ignoredException){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
