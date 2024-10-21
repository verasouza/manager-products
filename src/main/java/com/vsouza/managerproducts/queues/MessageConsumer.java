package com.vsouza.managerproducts.queues;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageConsumer{

    @RabbitListener(queues = "${rabbit-queues.message-queue}")
    public void receiveMessage(String message){
        log.info("MESSAGE_CONSUMER: Received message: {}", message);
    }

}
