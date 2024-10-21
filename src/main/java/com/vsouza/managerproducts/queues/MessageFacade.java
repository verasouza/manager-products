package com.vsouza.managerproducts.queues;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageFacade {

    private final MessageProducer producer;
    private final RabbitTemplate rabbitTemplate;

    @Retryable(retryFor = {AmqpException.class}, maxAttempts = 5, backoff = @Backoff(delay = 5000))
    public void sendMessage(String message) {
        CompletableFuture.supplyAsync(() -> {
            try {
                producer.send(message);
                return true;
            } catch (AmqpException e) {
                handleAmqpException(e.getMessage(), e, message);
                return false;
            } catch (Exception e) {
                handleGeneralException(e.getMessage(), e, message);
                return false;
            }

        });

    }

    /**
     * Handle AMQP specific exceptions
     */
    private void handleAmqpException(String message, AmqpException e, Object payload) {
        log.error("{}: {}", message, e.getMessage());
        log.debug("Exception details:", e);

        // Send to Dead Letter Queue
        try {
            rabbitTemplate.convertAndSend("dlx.exchange", "dlx.routing.key", payload);
            log.info("Message sent to DLQ after failure");
        } catch (Exception dlqException) {
            log.error("Failed to send message to DLQ: {}", dlqException.getMessage());
        }
    }

    /**
     * Handle general exceptions
     */
    private void handleGeneralException(String message, Exception e, Object payload) {
        log.error("{}: {}", message, e.getMessage());
        log.debug("Exception details:", e);
    }
}
