package br.com.vulcanodev.btgpactual_challenge.infrastructure;

import br.com.vulcanodev.btgpactual_challenge.domain.applications.ports.OrderRepositoryPort;
import br.com.vulcanodev.btgpactual_challenge.domain.applications.ports.OrderServicePort;
import br.com.vulcanodev.btgpactual_challenge.domain.applications.services.OrderService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeansConfig {
    @Bean
    public OrderServicePort orderServiceImpl(OrderRepositoryPort orderRepositoryPort) {
        return new OrderService(orderRepositoryPort);
    }
}
