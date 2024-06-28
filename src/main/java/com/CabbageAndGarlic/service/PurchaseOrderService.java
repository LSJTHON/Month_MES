package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.dto.ProductTotalDto;
import com.CabbageAndGarlic.dto.PurchaseOrderDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.entity.PurchaseOrder;
import com.CabbageAndGarlic.repository.OrderItemRepository;
import com.CabbageAndGarlic.repository.OrderRepository;
import com.CabbageAndGarlic.repository.PurchaseOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public List<Order> findOrdersNotInPurchaseOrder() {
        return orderRepository.findAll();
    }

    public List<OrderItem> findOrderItemsByOrderNumber(Long orderNumber) {
        Order order = orderRepository.findById(orderNumber).orElse(null);
        if (order == null) {
            throw new RuntimeException("Order not found with id: " + orderNumber);
        }
        return orderItemRepository.findByOrderNumber(order);
    }

    public List<Long> findOrderItemIdsByOrderNumbers(List<Long> orderNumbers) {
        List<Order> orders = orderRepository.findAllById(orderNumbers);
        return orderItemRepository.findByOrderNumberIn(orders).stream()
                .map(OrderItem::getOrderItemId)
                .collect(Collectors.toList());
    }

    public List<OrderItem> findOrderItemsByIds(List<Long> orderItemIds) {
        return orderItemRepository.findAllById(orderItemIds);
    }

    public List<ProductTotalDto> calculateTotals(List<Long> orderNumbers) {
        List<Order> orders = orderRepository.findAllById(orderNumbers);
        List<OrderItem> orderItems = orderItemRepository.findByOrderNumberIn(orders);
        Map<String, Integer> productTotals = new HashMap<>();
        for (OrderItem item : orderItems) {
            productTotals.merge(item.getProductName(), item.getAmount(), Integer::sum);
        }
        return productTotals.entrySet().stream()
                .map(entry -> new ProductTotalDto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<PurchaseOrderDto> getPurchaseOrderHistory() {
        return purchaseOrderRepository.findAll().stream()
                .map(po -> PurchaseOrderDto.builder()
                        .purchaseNumber(po.getPurchaseNumber())
                        .purchaseOrderStatus(po.getPurchaseStatus().toString())
                        .supplierName(po.getSupplierManage().getSupplierCode().getSupplierName()) // 발주처 이름
                        .materialName(po.getSupplierManage().getMaterialCode().getMaterialName()) // 자재명
                        .amount(po.getAmount())
                        .receiptDate(po.getReceiptDate())
                        .purchaseDate(po.getPurchaseDate())
                        .manager(po.getManager())
                        .build())
                .collect(Collectors.toList());
    }
}
