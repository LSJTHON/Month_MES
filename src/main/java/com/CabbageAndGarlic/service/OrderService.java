package com.CabbageAndGarlic.service;


import com.CabbageAndGarlic.constant.Status;
import com.CabbageAndGarlic.dto.OrderDto;
import com.CabbageAndGarlic.dto.OrderItemDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.repository.OrderItemRepository;
import com.CabbageAndGarlic.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Transactional
    public void saveOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderNumber(orderDto.getOrderNumber());
        order.setClient(orderDto.getClient());
        order.setStatus(Status.WAITING);
        order.setDeliveryDate(orderDto.getDeliveryDate());
        order.setPhoneNumber(orderDto.getPhoneNumber());

        order.setManager(orderDto.getManager());

        orderRepository.save(order);

        for (OrderItemDto itemDto : orderDto.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductName(itemDto.getProductName());
            orderItem.setAmount(itemDto.getAmount());
            orderItem.setOrderNumber(order);

            orderItemRepository.save(orderItem);
        }
    }

    //클릭된 수주행의 상세 품목정보 조회 서비스
    public List<OrderItem> findOrderItemsByOrderNumber(Long orderNumber) {
        Order order = orderRepository.findById(orderNumber)
                .orElseThrow(() -> new IllegalStateException("수주 번호를 찾을 수 없습니다.: " + orderNumber));
        return orderItemRepository.findByOrderNumber(order);
    }

    //임시 작업완료처리 서비스
    public void testComplete(Long orderNumber) {
        orderRepository.updateOrderStatusToCompleted(orderNumber);
    }
}
