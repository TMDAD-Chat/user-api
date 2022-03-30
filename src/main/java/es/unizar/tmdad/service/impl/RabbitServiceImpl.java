package es.unizar.tmdad.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.unizar.tmdad.adt.UserEvent;
import es.unizar.tmdad.service.RabbitService;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitServiceImpl implements RabbitService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Value("${chat.exchanges.output:user-api}")
    private String topicExchangeName;

    public RabbitServiceImpl(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @Override
    public void sendEvent(UserEvent event) {
        String eventAsString = objectMapper.writeValueAsString(event);
        this.rabbitTemplate.convertAndSend(topicExchangeName, "", eventAsString);
    }
}
