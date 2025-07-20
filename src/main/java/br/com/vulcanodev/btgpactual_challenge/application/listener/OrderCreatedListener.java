package br.com.vulcanodev.btgpactual_challenge.application.listener;

import br.com.vulcanodev.btgpactual_challenge.application.dtos.rabbitMq.OrderEventDto;
import br.com.vulcanodev.btgpactual_challenge.application.mappers.OrderMapper;
import br.com.vulcanodev.btgpactual_challenge.infrastructure.config.RabbitMqConfig;
import br.com.vulcanodev.btgpactual_challenge.domain.ports.OrderServicePort;
import br.com.vulcanodev.btgpactual_challenge.domain.model.Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    private final OrderServicePort orderServicePort;

    public OrderCreatedListener(OrderServicePort orderServicePort) {
        this.orderServicePort = orderServicePort;
    }

    @RabbitListener(queues = RabbitMqConfig.ORDER_CREATED_QUEUE)
    public void listen(OrderEventDto orderEvent) {
        try {
            logger.info("Message consumed for order: {}", orderEvent);
            Order savedOrder = orderServicePort.saveOrder(OrderMapper.eventToOrderModel(orderEvent));
            logger.info("Order saved: {}", savedOrder);
        } catch (Exception e) {
            logger.error("Error processing order created event for order: {}", orderEvent.codigoPedido(), e);
            throw e; // Re-throw para que o RabbitMQ possa tratar (retry, DLQ, etc.)
        }
    }
}
