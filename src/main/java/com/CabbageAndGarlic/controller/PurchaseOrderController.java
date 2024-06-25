package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/purchase")
public class PurchaseOrderController {

    private final OrderService orderService;

    @Autowired
    public PurchaseOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getPurchaseOrderPage(Model model) {
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "/Purchase/Purchase";
    }
}
