package com.elite.customer_app.service.impl;

import com.elite.customer_app.dto.CustomerDto;
import com.elite.customer_app.model.Customer;
import com.elite.customer_app.repository.CustomerRepository;
import com.elite.customer_app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDto> findAll() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::mapToCustomerDto).collect(Collectors.toList());
//        return customers.stream().map(customer -> mapToCustomerDto(customer)).collect(Collectors.toList());
    }

    @Override
    public Customer save(CustomerDto customerDto) {
        Customer customer = mapToCustomer(customerDto);
        return customerRepository.save(customer);
    }

    @Override
    public CustomerDto findById(long customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        return mapToCustomerDto( customer);
    }

    @Override
    public void update(CustomerDto customerDto) {
        Customer customer = mapToCustomer(customerDto);
        customerRepository.save(customer);
    }

    @Override
    public void delete(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<CustomerDto> searchCustomer(String query) {
        List<Customer> customers = customerRepository.searchCustomer(query);
        return customers.stream().map(customer -> mapToCustomerDto(customer)).collect(Collectors.toList());
    }

    private Customer mapToCustomer(CustomerDto customerDto) {
        Customer customer = Customer.builder()
                .id(customerDto.getId())
                .firstName(customerDto.getFirstName())
                .lastName(customerDto.getLastName())
                .email(customerDto.getEmail())
                .build();
        return customer;
    }

    private CustomerDto mapToCustomerDto(Customer customer){
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
