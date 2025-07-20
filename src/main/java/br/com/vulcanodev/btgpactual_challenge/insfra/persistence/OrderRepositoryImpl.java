package br.com.vulcanodev.btgpactual_challenge.insfra.persistence;

import org.springframework.stereotype.Repository;

import br.com.vulcanodev.btgpactual_challenge.domain.model.OrderEntity;
import br.com.vulcanodev.btgpactual_challenge.domain.repository.OrderRepository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderRepositoryMongo orderRepositoryMongo;

    public OrderRepositoryImpl(OrderRepositoryMongo orderRepositoryMongo) {
        this.orderRepositoryMongo = orderRepositoryMongo;
    }

    @Override
    public OrderEntity save(OrderEntity order) {
        if (order == null) {
            return null;
        }
        return orderRepositoryMongo.save(order);
    }
}