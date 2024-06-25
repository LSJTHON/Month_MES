package com.CabbageAndGarlic.controller;


import com.CabbageAndGarlic.dto.PurchaseOrderDto;
import com.CabbageAndGarlic.service.PurchaseOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseOrderApiController {

    private final PurchaseOrderService purchaseOrderService;

    @Autowired
    public PurchaseOrderApiController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @GetMapping("/orders")
    public List<PurchaseOrderDto> getAllPurchaseOrders() {
        return purchaseOrderService.getAllPurchaseOrders();
    }

    @PostMapping("/order")
    public void addPurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto) {
        purchaseOrderService.addPurchaseOrder(purchaseOrderDto);
    }

    @PostMapping("/updateStatus")
    public void updateOrderStatus() {
        purchaseOrderService.updateOrderStatus();
    }
}
