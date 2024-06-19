package com.CabbageAndGarlic.controller;


import com.CabbageAndGarlic.dto.OrderDto;
import com.CabbageAndGarlic.entity.Order;
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

    //수주정보 전부 가져오기
    @GetMapping("/orders")
    public Map<String,Object> getAllOrders() {

        Map<String,Object> getAllOrders = new HashMap<>();

        getAllOrders.put("data",orderService.findAll());

        return getAllOrders;
    }

    //캘린더에서 수주정보 모두 보여주기
    @GetMapping("/orderCalendar")
    public List<Order> getCalendarOrder() {
        return orderService.findAll();
    }



    //수주 등록하기
    @PostMapping("/createOrder")
    public String saveOrder(@RequestBody OrderDto orderDto) {
        orderService.saveOrder(orderDto);
        return "등록 성공";
    }


    //상품상세보기
    @GetMapping("/orderItems/{orderNumber}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderNumber(@PathVariable Long orderNumber) {
        List<OrderItem> items = orderService.findOrderItemsByOrderNumber(orderNumber);
        return ResponseEntity.ok(items);
    }
}
