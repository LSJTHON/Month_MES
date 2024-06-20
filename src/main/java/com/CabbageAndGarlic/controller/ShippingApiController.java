package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.constant.Status;
import com.CabbageAndGarlic.dto.ShippingDto;
import com.CabbageAndGarlic.dto.UpdateOrderDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.repository.OrderItemRepository;
import com.CabbageAndGarlic.repository.ShippingRepository;
import com.CabbageAndGarlic.service.OrderService;
import com.CabbageAndGarlic.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Shipping")
@RequiredArgsConstructor
public class ShippingApiController {

    private final OrderService orderService;
    private final ShippingService shippingService;
    private final OrderItemRepository orderItemRepository;


    //수주 정보를 전부 가져감 스크립트에서 완료된 항목만 보이게할 예정
    @GetMapping("/orders")
    public Map<String, Object> getAllOrders() {
        Map<String, Object> getAllOrders = new HashMap<>();
        getAllOrders.put("data", orderService.findAll());
        return getAllOrders;
    }

    //상품상세보기
    @GetMapping("/orderItems/{orderNumber}")
    public ResponseEntity<List<OrderItem>> getOrderItemsByOrderNumber(@PathVariable Long orderNumber) {
//        System.out.println(orderNumber+"수주번호 가져오나????????321472147985798235734723472347982347234798479823479823478");
        List<OrderItem> items = orderService.findOrderItemsByOrderNumber(orderNumber);

        System.out.println(items.size()+"품목은 몇개 나오냐????#@!?#?@!#?@!#?@!?#@!#?@!?");
        return ResponseEntity.ok(items);
    }

    // 출하 현황 데이터를 가져옴
    @GetMapping("/shippingStatus")
    public Map<String, Object> getShippingStatus() {
        Map<String, Object> shippingStatus = new HashMap<>();
        shippingStatus.put("data", shippingService.findPendingAndShippedOrders());
        return shippingStatus;
    }

    //출하버튼 클릭 시 받는 요청
    @PostMapping("/updateAndCreate")
    public void updateStatusAndCreateShipping(@RequestBody List<ShippingDto> request) throws IOException {
        shippingService.createShippings(request);
        shippingService.updateOrderStatus(request.stream().map(ShippingDto::getOrderNumber).toList(), "PendingShipment");
    }
}