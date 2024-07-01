package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.ProductTotalDto;
import com.CabbageAndGarlic.dto.PurchaseOrderRequest;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/purchase-order")
@RequiredArgsConstructor
public class PurchaseOrderApiController {

    private final PurchaseOrderService purchaseOrderService;

    @GetMapping("/orders-not-in-purchase")
    public List<Order> getOrdersNotInPurchaseOrder() {
        return purchaseOrderService.findOrdersNotInPurchaseOrder();
    }

    @GetMapping("/orderItems/{orderNumber}")
    public List<OrderItem> getOrderItems(@PathVariable Long orderNumber) {
        return purchaseOrderService.findOrderItemsByOrderNumber(orderNumber);
    }

    @PostMapping("/orderItemIds")
    public List<Long> getOrderItemIdsByOrderNumbers(@RequestBody List<Long> orderNumbers) {
        return purchaseOrderService.findOrderItemIdsByOrderNumbers(orderNumbers);
    }

    @PostMapping("/orderItemsByIds")
    public List<OrderItem> getOrderItemsByIds(@RequestBody List<Long> orderItemIds) {
        return purchaseOrderService.findOrderItemsByIds(orderItemIds);
    }

    @PostMapping("/calculateTotals")
    public List<ProductTotalDto> calculateTotals(@RequestBody List<Long> orderNumbers) {
        return purchaseOrderService.calculateTotals(orderNumbers);
    }


    @PostMapping("/create")
    public ResponseEntity<String> createPurchaseOrder(@RequestBody PurchaseOrderRequest request) {
        // 요청 데이터 검증
        if (request.getMaterialRequirements() == null || request.getMaterialRequirements().isEmpty()) {
            return new ResponseEntity<>("Material requirements are missing", HttpStatus.BAD_REQUEST);
        }
        if (request.getOrderNumbers() == null || request.getOrderNumbers().isEmpty()) {
            return new ResponseEntity<>("Order numbers are missing", HttpStatus.BAD_REQUEST);
        }

        // 요청 데이터 처리 (예: 데이터베이스 저장 등)
        // 여기에서는 단순히 요청 데이터를 로그로 출력하는 것으로 처리
        System.out.println("Received material requirements:");
        for (PurchaseOrderRequest.MaterialRequirement mr : request.getMaterialRequirements()) {
            System.out.println("Material: " + mr.getMaterialCode() + ", Total Amount: " + mr.getTotalAmount());
        }
        System.out.println("Received order numbers: " + request.getOrderNumbers());

        return new ResponseEntity<>("Purchase order created successfully", HttpStatus.OK);
    }
}
