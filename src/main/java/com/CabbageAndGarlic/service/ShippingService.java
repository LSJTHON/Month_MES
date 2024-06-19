package com.CabbageAndGarlic.service;


import com.CabbageAndGarlic.constant.Status;
import com.CabbageAndGarlic.dto.ShippingDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.Shipping;
import com.CabbageAndGarlic.repository.OrderRepository;
import com.CabbageAndGarlic.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ShippingService {

    private final ShippingRepository shippingRepository;

    private final OrderRepository orderRepository;


    //체크박스에 체크한 정보를 컨트롤로에서 받고 레포지토리 호출 후 수정
    public void updateOrderStatus(List<Long> orderNumbers, String status) {
        List<Order> orders = orderRepository.findAllById(orderNumbers);
        for (Order order : orders) {
            order.setStatus(Status.valueOf(status));
        }
        orderRepository.saveAll(orders);
    }

    public void createShippings(List<ShippingDto> shippingRequests) {
        for (ShippingDto request : shippingRequests) {
            Order order = orderRepository.findById(request.getOrderNumber()).orElseThrow(() -> new RuntimeException("Order not found"));
            Shipping shipping = new Shipping();
            shipping.setOrderNumber(order);
            shipping.setShippingCompany(request.getShippingCompany());
            shipping.setShippingDate(LocalDate.parse(request.getShippingDate()));
            shippingRepository.save(shipping);
        }
    }
    // PendingShipment, SHIPPED 상태인 수주 정보만 조회
    public List<ShippingDto> findPendingAndShippedOrders() {
        List<Shipping> shippings = shippingRepository.findAllByOrderNumber_StatusIn(List.of(Status.PendingShipment, Status.SHIPPED));
        System.out.println(shippings.size() + "개의 출하 정보가 있습니다."); // 디버깅용 출력
        return shippings.stream().map(shipping -> {
            ShippingDto dto = new ShippingDto();
            dto.setOrderNumber(shipping.getOrderNumber().getOrderNumber());
            dto.setShippingCompany(shipping.getShippingCompany());
            dto.setShippingDate(shipping.getShippingDate().toString());
            dto.setShippingStatus(shipping.getOrderNumber().getStatus().toString());
            return dto;
        }).collect(Collectors.toList());
    }
}