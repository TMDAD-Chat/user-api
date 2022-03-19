package es.unizar.tmdad.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class RabbitConfiguration {

    @Value("${chat.exchanges.output}")
    public String topicExchangeName;

    @Bean
    FanoutExchange exchange() {
        return new FanoutExchange(topicExchangeName, true, false);
    }
}
