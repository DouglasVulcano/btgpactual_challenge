package br.com.vulcanodev.btgpactual_challenge.domain.ports;

import java.util.List;

import br.com.vulcanodev.btgpactual_challenge.domain.model.Order;

public interface OrderServicePort {
    Order saveOrder(Order order);

    Order findById(Long orderId);

    List<Order> findAll();
}
