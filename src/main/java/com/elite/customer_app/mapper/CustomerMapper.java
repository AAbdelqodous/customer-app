package com.elite.customer_app.mapper;

import com.elite.customer_app.dto.CustomerDto;
import com.elite.customer_app.model.Customer;

import java.util.stream.Collectors;

public class CustomerMapper {

    public static Customer mapToCustomer(CustomerDto customerDto) {
        if (customerDto == null) {
            throw new IllegalArgumentException("CustomerDto cannot be null");
        }
        return Customer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .createdBy( customerDto.getCreatedBy())
                .build();
    }

    public static CustomerDto mapToCustomerDto(Customer customer){
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }
        return CustomerDto.builder()
                .id( customer.getId())
                .firstName( customer.getFirstName())
                .lastName( customer.getLastName())
                .email( customer.getEmail())
                .createdBy( customer.getCreatedBy())
                .orders( customer.getOrders().stream().map(OrderMapper::mapToOrderDto).collect(Collectors.toList()))
                .build();
    }

}
