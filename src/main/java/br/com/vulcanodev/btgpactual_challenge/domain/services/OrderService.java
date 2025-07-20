package br.com.vulcanodev.btgpactual_challenge.domain.services;

import br.com.vulcanodev.btgpactual_challenge.domain.ports.OrderRepositoryPort;
import br.com.vulcanodev.btgpactual_challenge.domain.ports.OrderServicePort;

import java.util.List;

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

    @Override
    public List<Order> findAll() {
        return this.orderRepositoryPort.findAll();
    }
}
