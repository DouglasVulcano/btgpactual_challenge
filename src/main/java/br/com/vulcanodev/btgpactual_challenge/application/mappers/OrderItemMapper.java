package br.com.vulcanodev.btgpactual_challenge.application.mappers;

import java.util.List;
import java.util.stream.Collectors;

import br.com.vulcanodev.btgpactual_challenge.application.dtos.rabbitMq.OrderItemEventDto;
import br.com.vulcanodev.btgpactual_challenge.application.entities.OrderItemEntity;
import br.com.vulcanodev.btgpactual_challenge.domain.model.OrderItem;

public class OrderItemMapper {

    public static OrderItemEntity toEntity(OrderItem item) {
        if (item == null) {
            return null;
        }
        OrderItemEntity entity = new OrderItemEntity();
        entity.setId(item.getId());
        entity.setProduct(item.getProduct());
        entity.setQuantity(item.getQuantity());
        entity.setPrice(item.getPrice());
        return entity;
    }

    public static OrderItem toModel(OrderItemEntity entity) {
        if (entity == null) {
            return null;
        }
        return new OrderItem(entity.getId(), entity.getProduct(), entity.getQuantity(), entity.getPrice());
    }

    public static OrderItem eventOrderItemToModel(OrderItemEventDto itemDto) {
        if (itemDto == null) {
            return null;
        }
        return new OrderItem(itemDto.produto(), itemDto.quantidade(), itemDto.preco());
    }

    public static List<OrderItemEntity> toEntityList(List<OrderItem> items) {
        if (items == null) {
            return null;
        }
        return items.stream()
                .map(OrderItemMapper::toEntity)
                .collect(Collectors.toList());
    }

    public static List<OrderItem> toModelList(List<OrderItemEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(OrderItemMapper::toModel)
                .collect(Collectors.toList());
    }

    public static List<OrderItem> eventToModelList(List<OrderItemEventDto> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(OrderItemMapper::eventOrderItemToModel)
                .collect(Collectors.toList());
    }

}
