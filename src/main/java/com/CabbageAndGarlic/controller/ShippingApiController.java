package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.constant.Status;
import com.CabbageAndGarlic.dto.ShippingDto;
import com.CabbageAndGarlic.dto.UpdateOrderDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.repository.ShippingRepository;
import com.CabbageAndGarlic.service.OrderService;
import com.CabbageAndGarlic.service.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Shipping")
@RequiredArgsConstructor
public class ShippingApiController {

    private final OrderService orderService;
    private final ShippingService shippingService;
    private final ShippingRepository shippingRepository;


    //수주 정보를 전부 가져감 스크립트에서 완료된 항목만 보이게할 예정
    @GetMapping("/orders")
    public Map<String, Object> getAllOrders() {
        Map<String, Object> getAllOrders = new HashMap<>();
        getAllOrders.put("data", orderService.findAll());
        return getAllOrders;
    }

    //출하버튼 클릭 시 받는 요청
    @PostMapping("/updateAndCreate")
    public void updateStatusAndCreateShipping(@RequestBody List<ShippingDto> request) {
        shippingService.createShippings(request);
        shippingService.updateOrderStatus(request.stream().map(ShippingDto::getOrderNumber).toList(), "PendingShipment");
    }
}