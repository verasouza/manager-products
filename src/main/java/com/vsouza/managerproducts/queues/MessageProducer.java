package com.vsouza.managerproducts.queues;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j @RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit-queues.exchange-message}")
    private String exchangeName;

    @Value("${rabbit-queues.exchange-route-key}")
    private String routingKey;

    public void send(String message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
