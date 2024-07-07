package com.elite.customer_app.service;

import com.elite.customer_app.dto.CustomerDto;
import com.elite.customer_app.model.Customer;

import java.util.List;

public interface CustomerService {

    List<CustomerDto> findAll();

    Customer save(CustomerDto customer);

    CustomerDto findById(long customerId);

    void update(CustomerDto customer);

    void delete(Long customerId);

    List<CustomerDto> searchCustomer(String query);
}
