package com.example.cakeshopapp.Web;

import com.example.cakeshopapp.Models.Order;
import com.example.cakeshopapp.Service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String getAllOrdersPage(Model model){
        List<Order> orders = this.orderService.listAll();
        model.addAttribute("orders", orders);
        return "orders.html";
    }
}
