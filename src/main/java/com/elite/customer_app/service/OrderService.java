package com.elite.customer_app.service;

import com.elite.customer_app.dto.CustomerDto;
import com.elite.customer_app.dto.OrderDto;

import java.util.List;

public interface OrderService {

    void createOrder(Long customerId, OrderDto orderDto);

    List<OrderDto> findAll();

    OrderDto findById(Long orderId);

    void update(OrderDto orderDto);

    void deleteOrder(long orderId);
}
