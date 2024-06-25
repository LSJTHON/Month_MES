package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.PurchaseOrderDto;
import com.CabbageAndGarlic.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchaseOrder")
@RequiredArgsConstructor
public class PurchaseOrderApiController {

    private final PurchaseOrderService purchaseOrderService;

    @GetMapping("/manage")
    public ResponseEntity<List<PurchaseOrderDto>> getAllPurchaseOrders() {
        List<PurchaseOrderDto> purchaseOrders = purchaseOrderService.getAllPurchaseOrders();
        return ResponseEntity.ok(purchaseOrders);
    }

    @PostMapping("/manage")
    public ResponseEntity<Void> addPurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto) {
        purchaseOrderService.addPurchaseOrder(purchaseOrderDto);
        return ResponseEntity.ok().build();
    }
}
