package com.elite.customer_app.mapper;

import com.elite.customer_app.dto.CustomerDto;
import com.elite.customer_app.model.Customer;

public class CustomerMapper {

    public static Customer mapToCustomer(CustomerDto customerDto) {
        Customer customer = Customer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .build();
        return customer;
    }

    public static CustomerDto mapToCustomerDto(Customer customer){
        return CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .build();
        /* CustomerDto customerDto = CustomerDto.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .build();
        return customerDto;*/
    }

}
