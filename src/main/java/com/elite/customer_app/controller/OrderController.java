package com.elite.customer_app.controller;

import com.elite.customer_app.dto.OrderDto;
import com.elite.customer_app.model.Order;
import com.elite.customer_app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
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
                              Model model){
        orderService.createOrder( customerId, orderDto);
        return "redirect:/customers/" + customerId;
    }
}
