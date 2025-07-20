package br.com.vulcanodev.btgpactual_challenge.application.port;

import br.com.vulcanodev.btgpactual_challenge.application.dto.rabbitMq.OrderCreatedEventDto;
import br.com.vulcanodev.btgpactual_challenge.domain.model.OrderEntity;

public interface OrderService {
    OrderEntity processOrderCreation(OrderCreatedEventDto orderEvent);
}
