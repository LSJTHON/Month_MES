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

    // 발주 내역에 포함되지 않은 모든 주문을 조회합니다.
    public List<Order> findOrdersNotInPurchaseOrder() {
        return orderRepository.findAll();
    }

    // 주문 번호에 해당하는 주문 상품들을 조회합니다.
    public List<OrderItem> findOrderItemsByOrderNumber(Long orderNumber) {
        Order order = orderRepository.findById(orderNumber).orElse(null);
        if (order == null) {
            throw new RuntimeException("주문 번호에 해당하는 주문을 찾을 수 없습니다: " + orderNumber);
        }
        return orderItemRepository.findByOrderNumber(order);
    }

    // 주문 번호들에 해당하는 주문 상품 ID들을 조회합니다.
    public List<Long> findOrderItemIdsByOrderNumbers(List<Long> orderNumbers) {
        List<Order> orders = orderRepository.findAllById(orderNumbers);
        return orderItemRepository.findByOrderNumberIn(orders).stream()
                .map(OrderItem::getOrderItemId)
                .collect(Collectors.toList());
    }

    // 주문 상품 ID들에 해당하는 주문 상품들을 조회합니다.
    public List<OrderItem> findOrderItemsByIds(List<Long> orderItemIds) {
        return orderItemRepository.findAllById(orderItemIds);
    }

    // 주문 번호들에 해당하는 제품 총액을 계산하여 반환합니다.
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

    // 발주 내역을 조회하여 PurchaseOrderDto 형태로 반환합니다.
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
