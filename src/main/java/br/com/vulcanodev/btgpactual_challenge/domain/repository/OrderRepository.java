package br.com.vulcanodev.btgpactual_challenge.domain.repository;

import br.com.vulcanodev.btgpactual_challenge.domain.model.OrderEntity;

public interface OrderRepository {
    OrderEntity save(OrderEntity order);
}