package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.ProductTotalDto;
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

    @PostMapping("/calculateTotals")
    public List<ProductTotalDto> calculateTotals(@RequestBody List<Long> orderNumbers) {
        return purchaseOrderService.calculateTotals(orderNumbers);
    }
}
