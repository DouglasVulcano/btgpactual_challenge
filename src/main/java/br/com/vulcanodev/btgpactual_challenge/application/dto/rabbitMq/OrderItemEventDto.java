package br.com.vulcanodev.btgpactual_challenge.application.dto.rabbitMq;

import java.math.BigDecimal;

public record OrderItemEventDto(
        String produto,
        Integer quantidade,
        BigDecimal preco) {
}
