package br.com.vulcanodev.btgpactual_challenge.application.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.vulcanodev.btgpactual_challenge.application.entities.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, Long> {
}
