package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.PurchaseOrderDto;
import com.CabbageAndGarlic.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase")
public class PurchaseOrderApiController {

//    private final PurchaseOrderService purchaseOrderService;
//
//    /**
//     * 주입된 PurchaseOrderService를 사용하여 PurchaseOrderApiController를 생성합니다.
//     *
//     * @param purchaseOrderService 발주 관리를 위한 서비스.
//     */
//    @Autowired
//    public PurchaseOrderApiController(PurchaseOrderService purchaseOrderService) {
//        this.purchaseOrderService = purchaseOrderService;
//    }
//
//    /**
//     * 모든 발주를 조회합니다.
//     *
//     * @return 모든 발주를 나타내는 PurchaseOrderDto의 리스트.
//     */
//    @GetMapping("/orders")
//    public List<PurchaseOrderDto> getAllPurchaseOrders() {
//        return purchaseOrderService.getAllPurchaseOrders();
//    }
//
//    /**
//     * 새로운 발주를 추가합니다.
//     *
//     * @param purchaseOrderDto 추가할 발주 데이터.
//     */
//    @PostMapping("/order")
//    public void addPurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto) {
//        purchaseOrderService.addPurchaseOrder(purchaseOrderDto);
//    }
//
//    /**
//     * 발주의 상태를 업데이트합니다.
//     */
//    @PostMapping("/updateStatus")
//    public void updateOrderStatus() {
//        purchaseOrderService.updateOrderStatus();
//    }
}
