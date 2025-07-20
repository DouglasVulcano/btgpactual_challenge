package br.com.vulcanodev.btgpactual_challenge.application.listener;

import br.com.vulcanodev.btgpactual_challenge.application.dto.rabbitMq.OrderCreatedEventDto;
import br.com.vulcanodev.btgpactual_challenge.config.RabbitMqConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedListener {

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);

    @RabbitListener(queues = RabbitMqConfig.ORDER_CREATED_QUEUE)
    public void listen(OrderCreatedEventDto orderEvent) {
        try {
            logger.info("Processing order created event: Order ID = {}, Customer ID = {}, Items count = {}", 
                    orderEvent.codigoPedido(), 
                    orderEvent.codigoCliente(), 
                    orderEvent.itens().size());
            
            // TODO: Implementar lógica de processamento do pedido
            // Exemplo: salvar no banco, enviar notificação, etc.
            
            logger.info("Order created event processed successfully for order: {}", orderEvent.codigoPedido());
        } catch (Exception e) {
            logger.error("Error processing order created event for order: {}", orderEvent.codigoPedido(), e);
            throw e; // Re-throw para que o RabbitMQ possa tratar (retry, DLQ, etc.)
        }
    }
}
