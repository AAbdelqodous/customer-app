package com.elite.customer_app.service;

import com.elite.customer_app.dto.OrderDto;

public interface OrderService {

    void createOrder(Long customerId, OrderDto orderDto);

}
