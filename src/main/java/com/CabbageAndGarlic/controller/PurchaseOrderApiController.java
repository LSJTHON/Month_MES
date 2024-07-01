package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.ProductTotalDto;
import com.CabbageAndGarlic.dto.PurchaseOrderDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-order")
@RequiredArgsConstructor
public class PurchaseOrderApiController {

    private final PurchaseOrderService purchaseOrderService;

    // 발주 내역에 포함되지 않은 주문들을 조회합니다.
    @GetMapping("/orders-not-in-purchase")
    public List<Order> getOrdersNotInPurchaseOrder() {
        return purchaseOrderService.findOrdersNotInPurchaseOrder();
    }

    // 주문 번호에 해당하는 주문 상품들을 조회합니다.
    @GetMapping("/orderItems/{orderNumber}")
    public List<OrderItem> getOrderItems(@PathVariable Long orderNumber) {
        return purchaseOrderService.findOrderItemsByOrderNumber(orderNumber);
    }

    // 주문 번호들에 대한 주문 상품 ID들을 조회합니다.
    @PostMapping("/orderItemIds")
    public List<Long> getOrderItemIdsByOrderNumbers(@RequestBody List<Long> orderNumbers) {
        return purchaseOrderService.findOrderItemIdsByOrderNumbers(orderNumbers);
    }

    // 주문 상품 ID들에 대한 주문 상품들을 조회합니다.
    @PostMapping("/orderItemsByIds")
    public List<OrderItem> getOrderItemsByIds(@RequestBody List<Long> orderItemIds) {
        return purchaseOrderService.findOrderItemsByIds(orderItemIds);
    }

    // 주문 번호들에 대한 제품 총액을 계산합니다.
    @PostMapping("/calculateTotals")
    public List<ProductTotalDto> calculateTotals(@RequestBody List<Long> orderNumbers) {
        return purchaseOrderService.calculateTotals(orderNumbers);
    }

    // 발주 내역을 조회합니다.
    @GetMapping("/history")
    public List<PurchaseOrderDto> getPurchaseOrderHistory() {
        return purchaseOrderService.getPurchaseOrderHistory();
    }
}
