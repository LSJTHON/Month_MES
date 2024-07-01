package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.constant.Status;
import com.CabbageAndGarlic.dto.ProductTotalDto;
import com.CabbageAndGarlic.dto.PurchaseOrderRequest;
import com.CabbageAndGarlic.dto.PurchaseOrderDto;
import com.CabbageAndGarlic.entity.*;
import com.CabbageAndGarlic.repository.MaterialRepository;
import com.CabbageAndGarlic.repository.OrderRepository;
import com.CabbageAndGarlic.repository.PurchaseOrderRepository;
import com.CabbageAndGarlic.service.OrderService;
import com.CabbageAndGarlic.service.PurchaseOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/purchase-order")
@RequiredArgsConstructor
public class PurchaseOrderApiController {

    private final PurchaseOrderService purchaseOrderService;

    private final PurchaseOrderRepository purchaseOrderRepository;

    private final OrderRepository orderRepository;

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

    @PostMapping("/create")
    public ResponseEntity<String> createPurchaseOrder(@RequestBody PurchaseOrderRequest request) {
        // 요청 데이터 검증
        if (request.getMaterialRequirements() == null || request.getMaterialRequirements().isEmpty()) {
            return new ResponseEntity<>("Material requirements are missing", HttpStatus.BAD_REQUEST);
        }
        if (request.getOrderNumbers() == null || request.getOrderNumbers().isEmpty()) {
            return new ResponseEntity<>("Order numbers are missing", HttpStatus.BAD_REQUEST);
        }


        for (PurchaseOrderRequest.MaterialRequirement mr : request.getMaterialRequirements()) {
            System.out.println("Material: " + mr.getMaterialCode() + ", Total Amount: " + mr.getTotalAmount());

            // PurchaseOrder 엔티티 생성 및 저장
            PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                    .purchaseStatus(Status.PurChased)
                    .materialName(mr.getMaterialCode() )
                    .amount((int) Math.ceil(mr.getTotalAmount()))
                    .receiptDate(LocalDateTime.now().plusDays(2))
                    .purchaseDate(LocalDateTime.now())
                    .manager("류치호") // 실제 담당자명을 입력해야 함
                    .supplierManage(null) // supplierManage를 null로 설정
                    // .order(null) // order를 null로 설정
                    .build();

            purchaseOrderRepository.save(purchaseOrder);
        }


        // 수주 상태 업데이트
        for (Long orderNumber : request.getOrderNumbers()) {
            Order order = orderRepository.findById(orderNumber)
                    .orElseThrow(() -> new RuntimeException("Order not found: " + orderNumber));
            order.setStatus(Status.PurChased);
            orderRepository.save(order);
        }


        return new ResponseEntity<>("Purchase order created successfully", HttpStatus.OK);
    }

    @GetMapping("/history")
    public Map<String, Object> getAllOrders() {
        Map<String, Object> getAllOrders = new HashMap<>();
        getAllOrders.put("data", purchaseOrderService.getAllPurchaseOrders());
        return getAllOrders;
    }

}