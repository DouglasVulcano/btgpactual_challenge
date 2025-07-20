package br.com.vulcanodev.btgpactual_challenge.application.service;

import org.springframework.stereotype.Service;

import br.com.vulcanodev.btgpactual_challenge.application.dto.rabbitMq.OrderCreatedEventDto;
import br.com.vulcanodev.btgpactual_challenge.application.mapper.orders.OrdersMapper;
import br.com.vulcanodev.btgpactual_challenge.application.port.OrderService;
import br.com.vulcanodev.btgpactual_challenge.domain.model.OrderEntity;
import br.com.vulcanodev.btgpactual_challenge.domain.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderEntity processOrderCreation(OrderCreatedEventDto orderEvent) {
        OrderEntity entity = OrdersMapper.toEntity(orderEvent);
        return orderRepository.save(entity);
    }
}
