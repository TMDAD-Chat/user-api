package es.unizar.tmdad.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class UserNotTheOwnerException extends RuntimeException {
	private String message;

}
