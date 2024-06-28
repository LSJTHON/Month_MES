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

    //발주내역 가져옴
    @GetMapping("/history")
    public List<PurchaseOrderDto> getPurchaseOrderHistory() {
        return purchaseOrderService.getPurchaseOrderHistory();
    }
}
