package br.com.vulcanodev.btgpactual_challenge.application.dto.rabbitMq;

import java.util.List;

public record OrderCreatedEventDto(
        Long codigoPedido,
        Long codigoCliente,
        List<OrderItemEventDto> itens) {
}
