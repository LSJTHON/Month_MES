package com.CabbageAndGarlic.controller;


import com.CabbageAndGarlic.dto.OrderDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/orders")
    public Map<String,Object> getAllOrders() {

        Map<String,Object> getAllOrders = new HashMap<>();

        getAllOrders.put("data",orderService.findAll());

        return getAllOrders;
    }


//    @PostMapping("/api/createOrders")
//    public ResponseEntity<Order> addOrder(@RequestBody OrderDto request){
//
//        Order savedOrder = orderService.save(request);
//
//        return ResponseEntity.ok()
//                .body(savedOrder);
//    }
}
