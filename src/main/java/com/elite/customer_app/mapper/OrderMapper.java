package com.elite.customer_app.mapper;

import com.elite.customer_app.dto.OrderDto;
import com.elite.customer_app.model.Order;

import static com.elite.customer_app.mapper.CustomerMapper.mapToCustomer;
import static com.elite.customer_app.mapper.CustomerMapper.mapToCustomerDto;

public class OrderMapper {

    public static Order mapToOrder(OrderDto orderDto){
        return Order.builder()
                .id( orderDto.getId())
                .orderDate( orderDto.getOrderDate())
                .amount( orderDto.getAmount())
                .customer( orderDto.getCustomer())
                .build();
    }

    public static OrderDto mapToOrderDto(Order order){
        return OrderDto.builder()
                .id( order.getId())
                .orderDate( order.getOrderDate())
                .amount( order.getAmount())
                .customer( order.getCustomer())
                .build();
    }
}
