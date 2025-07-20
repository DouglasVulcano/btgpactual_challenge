package br.com.vulcanodev.btgpactual_challenge.application.dtos.rabbitMq;

import java.util.List;

public record OrderEventDto(
        Long codigoPedido,
        Long codigoCliente,
        List<OrderItemEventDto> itens) {
}
