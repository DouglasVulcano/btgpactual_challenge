package br.com.vulcanodev.btgpactual_challenge.insfra.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.vulcanodev.btgpactual_challenge.domain.model.OrderEntity;

public interface OrderRepositoryMongo extends MongoRepository<OrderEntity, Long> {
}
