package br.com.vulcanodev.btgpactual_challenge.application.repositories.implementations;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.vulcanodev.btgpactual_challenge.application.entities.OrderEntity;
import br.com.vulcanodev.btgpactual_challenge.application.mappers.OrderMapper;
import br.com.vulcanodev.btgpactual_challenge.application.repositories.OrderRepository;
import br.com.vulcanodev.btgpactual_challenge.domain.ports.OrderRepositoryPort;
import br.com.vulcanodev.btgpactual_challenge.domain.model.Order;

@Repository
public class OrderMongoPort implements OrderRepositoryPort {

    private final OrderRepository orderRepository;

    public OrderMongoPort(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order saveOrder(Order order) {
        OrderEntity orderEntity = OrderMapper.toEntity(order);
        OrderEntity savedEntity = orderRepository.save(orderEntity);
        return OrderMapper.toModel(savedEntity);
    }

    @Override
    public Order findById(Long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
        return OrderMapper.toModel(orderEntity);
    }

    @Override
    public List<Order> findAll() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderEntities.stream()
                .map(OrderMapper::toModel)
                .toList();
    }
}