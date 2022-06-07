package es.unizar.tmdad.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class EntityNotFoundException extends RuntimeException {

	private EntityType type;
	private String message;

	public enum EntityType {
		USER, ROOM;
	}

}
