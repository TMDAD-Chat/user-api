package es.unizar.tmdad.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class AuthUserMismatchException extends RuntimeException {

	private String authedUser;
	private String user;

}
