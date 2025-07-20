package br.com.vulcanodev.btgpactual_challenge.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.vulcanodev.btgpactual_challenge.domain.ports.OrderRepositoryPort;
import br.com.vulcanodev.btgpactual_challenge.domain.ports.OrderServicePort;
import br.com.vulcanodev.btgpactual_challenge.domain.services.OrderService;

@Configuration
public class BeansConfig {
    @Bean
    public OrderServicePort orderServiceImpl(OrderRepositoryPort orderRepositoryPort) {
        return new OrderService(orderRepositoryPort);
    }
}
