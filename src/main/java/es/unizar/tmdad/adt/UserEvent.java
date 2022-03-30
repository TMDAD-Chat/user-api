package es.unizar.tmdad.adt;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEvent {

    private String subject; //Either the user id or the room id.
    private EventType event;
    private String argument; //Argument (i.e. user added to room or room added to user)

}
