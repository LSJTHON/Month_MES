package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.constant.Status;
import com.CabbageAndGarlic.dto.PurchaseOrderDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.PurchaseOrder;
import com.CabbageAndGarlic.entity.SupplierManage;
import com.CabbageAndGarlic.repository.OrderRepository;
import com.CabbageAndGarlic.repository.PurchaseOrderRepository;
import com.CabbageAndGarlic.repository.SupplierManageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SupplierManageRepository supplierManageRepository;
    private final OrderRepository orderRepository;

    /**
     * PurchaseOrderService 생성자.
     *
     * @param purchaseOrderRepository 발주를 관리하는 레포지토리.
     * @param supplierManageRepository 공급자 관리를 위한 레포지토리.
     * @param orderRepository 수주를 관리하는 레포지토리.
     */
    @Autowired
    public PurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository, SupplierManageRepository supplierManageRepository, OrderRepository orderRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.supplierManageRepository = supplierManageRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * 모든 발주를 조회합니다.
     *
     * @return 모든 발주를 나타내는 PurchaseOrderDto의 리스트.
     */
    public List<PurchaseOrderDto> getAllPurchaseOrders() {
        List<PurchaseOrder> orders = purchaseOrderRepository.findAll();
        return orders.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    /**
     * 새로운 발주를 추가합니다.
     *
     * @param purchaseOrderDto 추가할 발주 데이터.
     */
    @Transactional
    public void addPurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
        SupplierManage supplierManage = supplierManageRepository.findById(purchaseOrderDto.getSupplierManageId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid SupplierManage ID: " + purchaseOrderDto.getSupplierManageId()));
        Order order = orderRepository.findById(purchaseOrderDto.getOrderNumber())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Order ID: " + purchaseOrderDto.getOrderNumber()));

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setPurchaseNumber(purchaseOrderDto.getPurchaseNumber());
        purchaseOrder.setPurchaseStatus(Status.valueOf(purchaseOrderDto.getPurchaseOrderStatus()));
        purchaseOrder.setSupplierManage(supplierManage);
        purchaseOrder.setOrder(order);
        purchaseOrder.setAmount(purchaseOrderDto.getAmount());
        purchaseOrder.setReceiptDate(purchaseOrderDto.getReceiptDate());
        purchaseOrder.setPurchaseDate(purchaseOrderDto.getPurchaseDate());

        purchaseOrderRepository.save(purchaseOrder);
    }

    /**
     * 발주의 상태를 업데이트합니다.
     */
    public void updateOrderStatus() {
        List<PurchaseOrder> orders = purchaseOrderRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        for (PurchaseOrder order : orders) {
            if (order.getReceiptDate().isBefore(now) && order.getPurchaseStatus() == Status.ORDERED) {
                order.setPurchaseStatus(Status.RECEIVED);
                purchaseOrderRepository.save(order);
            }
        }
    }

    /**
     * PurchaseOrder 엔티티를 PurchaseOrderDto로 변환합니다.
     *
     * @param order 변환할 발주 엔티티.
     * @return 변환된 PurchaseOrderDto.
     */
    private PurchaseOrderDto convertToDto(PurchaseOrder order) {
        PurchaseOrderDto dto = new PurchaseOrderDto();
        dto.setPurchaseNumber(order.getPurchaseNumber());
        dto.setPurchaseOrderStatus(order.getPurchaseStatus().name());
        dto.setSupplierManageId(order.getSupplierManage().getId());
        dto.setOrderNumber(order.getOrder().getOrderNumber());
        dto.setAmount(order.getAmount());
        dto.setReceiptDate(order.getReceiptDate());
        dto.setPurchaseDate(order.getPurchaseDate());
        return dto;
    }
}
