package com.elite.customer_app.service.impl;

import com.elite.customer_app.dto.CustomerDto;
import com.elite.customer_app.dto.OrderDto;
import com.elite.customer_app.mapper.CustomerMapper;
import com.elite.customer_app.mapper.OrderMapper;
import com.elite.customer_app.model.Customer;
import com.elite.customer_app.model.Order;
import com.elite.customer_app.repository.CustomerRepository;
import com.elite.customer_app.repository.OrderRepository;
import com.elite.customer_app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.elite.customer_app.mapper.OrderMapper.mapToOrder;
import static com.elite.customer_app.mapper.OrderMapper.mapToOrderDto;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Override
    public List<OrderDto> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(OrderMapper::mapToOrderDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(Long orderId) {
        Order order = orderRepository.findById(orderId).get();
        return mapToOrderDto(order);
    }

    @Override
    public void update(OrderDto orderDto) {
        Order order = mapToOrder(orderDto);
        if (orderDto.getCustomer() == null) {
            throw new IllegalArgumentException("CustomerDto cannot be null");
        }
        order.setCustomer(customerRepository.findById(orderDto.getCustomer().getId()).orElseThrow(() -> new IllegalArgumentException("Invalid customer Id")));
        orderRepository.save(order);
    }

    @Override
    public void deleteOrder(long orderId) {
        orderRepository.deleteById(orderId);
    }

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
