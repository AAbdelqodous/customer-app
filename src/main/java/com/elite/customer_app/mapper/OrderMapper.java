package com.elite.customer_app.mapper;

import com.elite.customer_app.dto.OrderDto;
import com.elite.customer_app.model.Order;

public class OrderMapper {

    public static Order mapToOrder(OrderDto orderDto){
        return Order.builder()
                .id( orderDto.getId())
                .orderDate( orderDto.getOrderDate())
                .amount( orderDto.getAmount())
                .build();
    }
}
