package com.elite.customer_app.controller;

import com.elite.customer_app.dto.CustomerDto;
import com.elite.customer_app.dto.OrderDto;
import com.elite.customer_app.model.Customer;
import com.elite.customer_app.model.Order;
import com.elite.customer_app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String customerList(Model model){
        List<OrderDto> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "orders-list";
    }

    @GetMapping("/orders/{orderId}")
    public String findOrder(@PathVariable("orderId") Long orderId, Model model){
        OrderDto order = orderService.findById(orderId);
        model.addAttribute("order", order);
        return "order-details";
    }

    @GetMapping("/orders/{customerId}/new")
    public String createOrderForm(@PathVariable("customerId") Long customerId, Model model){
        Order order = new Order();
        model.addAttribute("customerId", customerId);
        model.addAttribute("order", order);
        return "orders-create";
    }

    @PostMapping("/orders/{customerId}")
    public String createOrder(@PathVariable("customerId") Long customerId,
                              @ModelAttribute("order") OrderDto orderDto,
                              Model model,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("order", orderDto);
            return "orders-create";
        }
        orderService.createOrder( customerId, orderDto);
        return "redirect:/customers/" + customerId;
    }

    @GetMapping("/orders/{orderId}/edit")
    public String editOrderForm(@PathVariable("orderId") Long orderId,
                                Model model){
        OrderDto order = orderService.findById(orderId);
        model.addAttribute("order", order);
        return "order-edit";
    }

    @PostMapping("/orders/{orderId}/edit")
    public String updateOrder(@PathVariable("orderId") Long orderId,
                              @ModelAttribute("order") OrderDto orderDto,
                              Model model,
                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("order", orderDto);
            return "order-details";
        }
        System.out.println("$$.. orderId: " +orderId);
        OrderDto orderDtoById = orderService.findById(orderId);
        Customer customer = orderDtoById.getCustomer();
        if (customer == null) {
            throw new IllegalArgumentException("CustomerDto cannot be null");
        }

        orderDto.setId( orderId);
        orderDto.setCustomer( customer);
        orderService.update( orderDto);
        return "redirect:/orders";
    }

    @GetMapping("/orders/{orderId}/delete")
    public String deleteOrder(@PathVariable("orderId") long orderId){
        orderService.deleteOrder(orderId);
        return "redirect:/orders";
    }
}
