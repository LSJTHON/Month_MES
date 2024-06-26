package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.dto.ProductTotalDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.OrderItem;
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
        // 모든 수주 목록 가져오기
        List<Order> allOrders = orderRepository.findAll();

        // 발주 목록에 있는 수주 번호 가져오기
        List<Long> purchaseOrderNumbers = purchaseOrderRepository.findAll().stream()
                .map(po -> po.getOrder().getOrderNumber())
                .collect(Collectors.toList());

        // 발주 목록에 없는 수주만 필터링
        return allOrders.stream()
                .filter(order -> !purchaseOrderNumbers.contains(order.getOrderNumber()))
                .collect(Collectors.toList());
    }

    public List<OrderItem> findOrderItemsByOrderNumber(Long orderNumber) {
        Order order = orderRepository.findById(orderNumber).orElse(null);
        if (order == null) {
            throw new RuntimeException("Order not found with id: " + orderNumber);
        }
        return orderItemRepository.findByOrderNumber(order);
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
}
