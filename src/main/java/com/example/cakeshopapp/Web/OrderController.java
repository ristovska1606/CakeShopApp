package com.example.cakeshopapp.Web;

import com.example.cakeshopapp.Models.Order;
import com.example.cakeshopapp.Models.enums.OrderStatus;
import com.example.cakeshopapp.Service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String getAllOrdersPage(Model model){
        List<Order> orders = this.orderService.listAll().stream()
                .filter(o -> o.getStatus().equals(OrderStatus.IN_PROGRESS))
                .collect(Collectors.toList());
        model.addAttribute("orders", orders);
        return "orders.html";
    }

    @PostMapping("/orders/markAsFinished/{id}")
    public String markAsFinished(@PathVariable Long id){
        this.orderService.markAsFinished(id);
        return "redirect:/orders";

    }
}
