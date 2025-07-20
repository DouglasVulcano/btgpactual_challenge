package br.com.vulcanodev.btgpactual_challenge.application.listener;

import br.com.vulcanodev.btgpactual_challenge.application.dto.rabbitMq.OrderCreatedEventDto;
import br.com.vulcanodev.btgpactual_challenge.application.port.OrderService;
import br.com.vulcanodev.btgpactual_challenge.config.RabbitMqConfig;
import br.com.vulcanodev.btgpactual_challenge.domain.model.OrderEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);
    private final OrderService orderService;

    public OrderCreatedListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = RabbitMqConfig.ORDER_CREATED_QUEUE)
    public void listen(OrderCreatedEventDto orderEvent) {
        try {
            logger.info("Message consumed for order: {}", orderEvent);
            OrderEntity savedOrder = orderService.processOrderCreation(orderEvent);
            logger.info("Order saved: {}", savedOrder);
        } catch (Exception e) {
            logger.error("Error processing order created event for order: {}", orderEvent.codigoPedido(), e);
            throw e; // Re-throw para que o RabbitMQ possa tratar (retry, DLQ, etc.)
        }
    }
}
