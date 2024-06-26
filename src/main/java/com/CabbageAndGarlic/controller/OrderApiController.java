package com.CabbageAndGarlic.controller;


import com.CabbageAndGarlic.dto.OrderDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.service.OrderItemService;
import com.CabbageAndGarlic.service.OrderService;
import com.CabbageAndGarlic.service.ProductionPlanService;
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
    private final ProductionPlanService productionPlanService;

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
<<<<<<< HEAD
        productionPlanService.savePlan(orderDto);

        return "등록 성공";
=======
        return "수주를 정상적으로 등록했습니다.";
>>>>>>> 8025c6c7d65d6da6a16b442faf6bf0dd7576510c
    }


    //상품상세보기
    @GetMapping("/orderItems/{orderNumber}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderNumber(@PathVariable Long orderNumber) {
        List<OrderItem> items = orderService.findOrderItemsByOrderNumber(orderNumber);
        return ResponseEntity.ok(items);
    }


    //상품 작업상태 임시 수정 메소드
    @PutMapping("/testComplete/{orderNumber}")
    public void testComplete(@PathVariable Long orderNumber) {
        orderService.testComplete(orderNumber);
    }
}
