package com.elite.customer_app.controller;

import com.elite.customer_app.dto.CustomerDto;
import com.elite.customer_app.model.Customer;
import com.elite.customer_app.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public String customerList(Model model){
        List<CustomerDto> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        return "customers-list";
    }

    @GetMapping("/customers/{customerId}")
    public String customerDetails(@PathVariable("customerId") Long customerId,
                                  Model model){
        CustomerDto customerDto = customerService.findById(customerId);
        model.addAttribute("customer", customerDto);
        return "customer-details";
    }

    @GetMapping("/customers/new")
    public String showCreateCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customers-create";
    }

    @PostMapping("/customers/new")
    public String saveCustomer(@Valid @ModelAttribute("customer") CustomerDto customerDto,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("customer", customerDto);
            return "customers-create";
        }
        customerService.save(customerDto);
        return "redirect:/customers";
    }

    @GetMapping("/customers/{customerId}/edit")
    public String editCustomer(@PathVariable("customerId") Long customerId, Model model){
        CustomerDto customer = customerService.findById(customerId);
        model.addAttribute("customer", customer);
        return "customer-edit";
    }

    @PostMapping("/customers/{customerId}/edit")
    public String updateCustomer(@PathVariable("customerId") Long customerId,
                                 @Valid @ModelAttribute("customer") CustomerDto customer,
                                 BindingResult bindingResult,
                                 Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("customer", customer);
            return "customer-edit";
        }
        customer.setId(customerId);
        customerService.update(customer);
        return "redirect:/customers";
    }

    @GetMapping("/customers/{customerId}/delete")
    public String deleteCustomer(@PathVariable("customerId") Long customerId){
        customerService.delete(customerId);
        return "redirect:/customers";
    }

    @GetMapping("/customers/search")
    public String searchCustomers(@RequestParam(value = "query") String query, Model model){
        List<CustomerDto> customers = customerService.searchCustomer(query);
        model.addAttribute("customers", customers);
        return "/customers-list";
    }
}
