package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.PurchaseOrderDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.PurchaseOrder;
import com.CabbageAndGarlic.service.OrderService;
import com.CabbageAndGarlic.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-order")
@RequiredArgsConstructor // Lombok 라이브러리를 사용하여 final 필드를 포함한 생성자를 자동 생성
public class PurchaseOrderApiController {

    private final PurchaseOrderService purchaseOrderService;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createPurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto) {
        purchaseOrderService.createPurchaseOrder(purchaseOrderDto); // 서비스를 통해 발주를 생성
        return ResponseEntity.ok("발주 등록 성공"); // 성공 응답 메시지 반환
    }

    @GetMapping("/orders-not-in-purchase")
    public ResponseEntity<List<Order>> getOrdersNotInPurchaseOrder() {
        List<Order> orders = orderService.findOrdersNotInPurchaseOrder(); // 발주에 포함되지 않은 주문을 찾아서 반환
        return ResponseEntity.ok(orders); // 주문 목록을 응답으로 반환
    }

    @GetMapping // HTTP GET 요청을 처리하는 메소드
    public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderService.findAll(); // 모든 발주 정보를 조회
        return ResponseEntity.ok(purchaseOrders); // 발주 목록을 응답으로 반환
    }
}
