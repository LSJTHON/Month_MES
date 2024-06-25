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
        SupplierManage supplierManage = supplierManageRepository.findById(purchaseOrderDto.getSupplierManageId())
                .orElseThrow(() -> new RuntimeException("SupplierManage not found"));
        Order order = orderRepository.findById(purchaseOrderDto.getOrderNumber())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .supplierManage(supplierManage)
                .order(order)
                .amount(purchaseOrderDto.getAmount())
                .receiptDate(purchaseOrderDto.getReceiptDate())
                .purchaseDate(purchaseOrderDto.getPurchaseDate())
                .manager(purchaseOrderDto.getManager())
                .build();

        purchaseOrderRepository.save(purchaseOrder);
    }

    public List<PurchaseOrder> findAll() {
        return purchaseOrderRepository.findAll();
    }
}
