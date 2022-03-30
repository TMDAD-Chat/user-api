package es.unizar.tmdad.service;

import es.unizar.tmdad.adt.UserEvent;

public interface RabbitService {

    void sendEvent(UserEvent event);

}
