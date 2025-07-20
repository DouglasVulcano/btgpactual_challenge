package br.com.vulcanodev.btgpactual_challenge.domain.applications.services;

import br.com.vulcanodev.btgpactual_challenge.domain.applications.ports.OrderRepositoryPort;
import br.com.vulcanodev.btgpactual_challenge.domain.applications.ports.OrderServicePort;
import br.com.vulcanodev.btgpactual_challenge.domain.model.Order;

public class OrderService implements OrderServicePort {

    private final OrderRepositoryPort orderRepositoryPort;

    public OrderService(OrderRepositoryPort orderRepositoryPort) {
        this.orderRepositoryPort = orderRepositoryPort;
    }

    @Override
    public Order saveOrder(Order order) {
        return this.orderRepositoryPort.saveOrder(order);
    }

    @Override
    public Order findById(Long orderId) {
        return this.orderRepositoryPort.findById(orderId);
    }
}
