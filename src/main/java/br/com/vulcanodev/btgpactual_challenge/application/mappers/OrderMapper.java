package br.com.vulcanodev.btgpactual_challenge.application.mappers;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.vulcanodev.btgpactual_challenge.application.dtos.rabbitMq.OrderCreatedEventDto;
import br.com.vulcanodev.btgpactual_challenge.application.dtos.rabbitMq.OrderItemEventDto;
import br.com.vulcanodev.btgpactual_challenge.application.entities.OrderEntity;
import br.com.vulcanodev.btgpactual_challenge.application.entities.OrderItemEntity;
import br.com.vulcanodev.btgpactual_challenge.domain.model.Order;

public class OrderMapper {
    public static OrderEntity toEntity(Order order) {
        if (order == null) {
            return null;
        }
        return new OrderEntity(order.getId(), order.getCustomerId(), order.getTotal(),
                OrderItemMapper.toEntityList(order.getItems()));
    }

    public static Order toModel(OrderEntity entity) {
        if (entity == null) {
            return null;
        }
        return new Order(entity.getId(), entity.getCustomerId(), entity.getTotal(),
                OrderItemMapper.toModelList(entity.getItems()));
    }

    public static Order eventToOrderModel(OrderCreatedEventDto orderCreatedEventDto) {
        if (orderCreatedEventDto == null) {
            return null;
        }
        return new Order(orderCreatedEventDto.codigoPedido(), orderCreatedEventDto.codigoCliente(),
                calculateTotal(orderCreatedEventDto.itens()),
                OrderItemMapper.eventToModelList(orderCreatedEventDto.itens()));
    }

    public static OrderItemEntity toOrderItemEntity(OrderItemEventDto itemDto) {
        if (itemDto == null) {
            return null;
        }
        return new OrderItemEntity(itemDto.produto(), itemDto.quantidade(), itemDto.preco());
    }

    private static BigDecimal calculateTotal(List<OrderItemEventDto> items) {
        if (items == null || items.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return items.stream()
                .map(item -> item.preco().multiply(BigDecimal.valueOf(item.quantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public static List<OrderItemEntity> toItemEntities(List<OrderItemEventDto> itemDtos) {
        if (itemDtos == null) {
            return null;
        }
        return itemDtos.stream()
                .map(OrderMapper::toOrderItemEntity)
                .collect(Collectors.toList());
    }
}
