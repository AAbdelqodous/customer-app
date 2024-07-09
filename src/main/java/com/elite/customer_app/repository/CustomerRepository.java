package com.elite.customer_app.repository;

import com.elite.customer_app.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.lastName LIKE CONCAT('%', :query, '%')")
    List<Customer> searchCustomer(String query);

}
