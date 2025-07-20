package br.com.vulcanodev.btgpactual_challenge.domain.applications.ports;

import br.com.vulcanodev.btgpactual_challenge.domain.model.Order;

public interface OrderRepositoryPort {
    Order saveOrder(Order order);

    Order findById(Long orderId);
}