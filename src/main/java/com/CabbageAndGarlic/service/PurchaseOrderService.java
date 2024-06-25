package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.constant.Status;
import com.CabbageAndGarlic.dto.PurchaseOrderDto;
import com.CabbageAndGarlic.entity.PurchaseOrder;
import com.CabbageAndGarlic.entity.SupplierManage;
import com.CabbageAndGarlic.repository.PurchaseOrderRepository;
import com.CabbageAndGarlic.repository.SupplierManageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SupplierManageRepository supplierManageRepository;

    public List<PurchaseOrderDto> getAllPurchaseOrders() {
        List<PurchaseOrder> purchaseOrders = purchaseOrderRepository.findAll();
        return purchaseOrders.stream().map(this::toDto).collect(Collectors.toList());
    }

    public void addPurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
        SupplierManage supplierManage = supplierManageRepository.findById(purchaseOrderDto.getSupplierManageId())
                .orElseThrow(() -> new RuntimeException("SupplierManage not found"));

        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .purchaseStatus(Status.valueOf(purchaseOrderDto.getPurchaseOrderStatus()))
                .supplierManage(supplierManage)
                .amount(purchaseOrderDto.getAmount())
                .receiptDate(purchaseOrderDto.getReceiptDate())
                .purchaseDate(purchaseOrderDto.getPurchaseDate())
                .manager(purchaseOrderDto.getManager())
                .build();

        purchaseOrderRepository.save(purchaseOrder);
    }

    private PurchaseOrderDto toDto(PurchaseOrder purchaseOrder) {
        return PurchaseOrderDto.builder()
                .purchaseNumber(purchaseOrder.getPurchaseNumber())
                .purchaseOrderStatus(purchaseOrder.getPurchaseStatus().name())
                .supplierManageId(purchaseOrder.getSupplierManage().getId())
                .amount(purchaseOrder.getAmount())
                .receiptDate(purchaseOrder.getReceiptDate())
                .purchaseDate(purchaseOrder.getPurchaseDate())
                .manager(purchaseOrder.getManager())
                .build();
    }
}
