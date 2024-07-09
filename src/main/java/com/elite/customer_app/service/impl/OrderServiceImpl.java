package com.elite.customer_app.service.impl;

import com.elite.customer_app.dto.OrderDto;
import com.elite.customer_app.model.Customer;
import com.elite.customer_app.model.Order;
import com.elite.customer_app.repository.CustomerRepository;
import com.elite.customer_app.repository.OrderRepository;
import com.elite.customer_app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.elite.customer_app.mapper.OrderMapper.mapToOrder;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void createOrder(Long customerId, OrderDto orderDto) {
        Customer customer = customerRepository.getById(customerId);
        Order order = mapToOrder(orderDto);
        order.setCustomer(customer);
        orderRepository.save(order);
    }
}
