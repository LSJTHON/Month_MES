package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.dto.PurchaseOrderDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.PurchaseOrder;
import com.CabbageAndGarlic.entity.SupplierManage;
import com.CabbageAndGarlic.repository.OrderRepository;
import com.CabbageAndGarlic.repository.PurchaseOrderRepository;
import com.CabbageAndGarlic.repository.SupplierManageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SupplierManageRepository supplierManageRepository;
    private final OrderRepository orderRepository;

    public void createPurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
        // 구매 주문 생성
        SupplierManage supplierManage = supplierManageRepository.findById(purchaseOrderDto.getSupplierManageId())
                .orElseThrow(() -> new RuntimeException("SupplierManage not found")); // 공급자 관리 ID로 조회 실패 시 예외 발생
        Order order = orderRepository.findById(purchaseOrderDto.getOrderNumber())
                .orElseThrow(() -> new RuntimeException("Order not found")); // 주문 번호로 조회 실패 시 예외 발생

        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .supplierManage(supplierManage) // 공급자 정보 설정
                .order(order) // 주문 정보 설정
                .amount(purchaseOrderDto.getAmount()) // 구매 금액 설정
                .receiptDate(purchaseOrderDto.getReceiptDate()) // 수령 예정일 설정
                .purchaseDate(purchaseOrderDto.getPurchaseDate()) // 구매일 설정
                .manager(purchaseOrderDto.getManager()) // 담당자 설정
                .build();

        purchaseOrderRepository.save(purchaseOrder); // 구매 주문 저장
    }

    public List<PurchaseOrder> findAll() {
        // 모든 구매 주문 정보 조회
        return purchaseOrderRepository.findAll();
    }
}
