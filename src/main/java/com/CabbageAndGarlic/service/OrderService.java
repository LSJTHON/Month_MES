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
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
//        order.setStatus(Status.valueOf(orderDto.getStatus()));
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

}
