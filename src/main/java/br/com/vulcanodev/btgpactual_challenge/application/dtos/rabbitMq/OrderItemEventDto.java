package br.com.vulcanodev.btgpactual_challenge.application.dtos.rabbitMq;

import java.math.BigDecimal;

public record OrderItemEventDto(
        String produto,
        Integer quantidade,
        BigDecimal preco) {
}
