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
@RequiredArgsConstructor
public class PurchaseOrderApiController {

    private final PurchaseOrderService purchaseOrderService;
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<String> createPurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto) {
        purchaseOrderService.createPurchaseOrder(purchaseOrderDto);
        return ResponseEntity.ok("발주 등록 성공");
    }

    @GetMapping("/orders-not-in-purchase")
    public ResponseEntity<List<Order>> getOrdersNotInPurchaseOrder() {
        List<Order> orders = orderService.findOrdersNotInPurchaseOrder();
        return ResponseEntity.ok(orders);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderService.findAll();
        return ResponseEntity.ok(purchaseOrders);
    }
}
