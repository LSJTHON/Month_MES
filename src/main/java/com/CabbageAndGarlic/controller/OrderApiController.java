package com.CabbageAndGarlic.controller;


import com.CabbageAndGarlic.dto.OrderDto;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;


    @GetMapping("/orders")
    public Map<String,Object> getAllOrders() {

        Map<String,Object> getAllOrders = new HashMap<>();

        getAllOrders.put("data",orderService.findAll());

        return getAllOrders;
    }

    @PostMapping("/createOrder")
    public String saveOrder(@RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderDto);
        return "등록 성공";
    }


//    @GetMapping("/orderItems/{orderNumber}")
//    public Map<String,Object> getOrderItemsByOrderNumber(@PathVariable Long orderNumber) {
//        Map<String,Object> getOrderItems = new HashMap<>();
//
//        System.out.println(orderNumber+"이게 수주번호다 이말이야");
//
//        getOrderItems.put("data",orderService.findById(orderNumber));
//
//        return getOrderItems;
//    }

    @GetMapping("/orderItems/{orderNumber}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderNumber(@PathVariable Long orderNumber) {
        List<OrderItem> items = orderService.findOrderItemsByOrderNumber(orderNumber);
        return ResponseEntity.ok(items);
    }

}
