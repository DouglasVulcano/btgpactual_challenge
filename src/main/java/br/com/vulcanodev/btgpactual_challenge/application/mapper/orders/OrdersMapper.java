package br.com.vulcanodev.btgpactual_challenge.application.mapper.orders;

import br.com.vulcanodev.btgpactual_challenge.application.dto.rabbitMq.OrderCreatedEventDto;
import br.com.vulcanodev.btgpactual_challenge.application.dto.rabbitMq.OrderItemEventDto;
import br.com.vulcanodev.btgpactual_challenge.domain.model.OrderEntity;
import br.com.vulcanodev.btgpactual_challenge.domain.model.OrderItemEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersMapper {
    
    public static OrderEntity toEntity(OrderCreatedEventDto orderDto) {
        if (orderDto == null) {
            return null;
        }
        
        OrderEntity entity = new OrderEntity();
        entity.setId(orderDto.codigoPedido());
        entity.setCustomerId(orderDto.codigoCliente());
        entity.setItems(toItemEntities(orderDto.itens()));
        entity.setTotal(calculateTotal(orderDto.itens()));
        
        return entity;
    }
    
    public static List<OrderItemEntity> toItemEntities(List<OrderItemEventDto> itemDtos) {
        if (itemDtos == null) {
            return null;
        }
        
        return itemDtos.stream()
                .map(OrdersMapper::toItemEntity)
                .collect(Collectors.toList());
    }
    
    public static OrderItemEntity toItemEntity(OrderItemEventDto itemDto) {
        if (itemDto == null) {
            return null;
        }
        
        OrderItemEntity entity = new OrderItemEntity();
        entity.setProduct(itemDto.produto());
        entity.setQuantity(itemDto.quantidade());
        entity.setPrice(itemDto.preco());
        
        return entity;
    }
    
    private static BigDecimal calculateTotal(List<OrderItemEventDto> items) {
        if (items == null || items.isEmpty()) {
            return BigDecimal.ZERO;
        }
        
        return items.stream()
                .map(item -> item.preco().multiply(BigDecimal.valueOf(item.quantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
