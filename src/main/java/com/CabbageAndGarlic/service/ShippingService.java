package com.CabbageAndGarlic.service;


import com.CabbageAndGarlic.constant.Status;
import com.CabbageAndGarlic.dto.ShippingDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.entity.Shipping;
import com.CabbageAndGarlic.repository.OrderItemRepository;
import com.CabbageAndGarlic.repository.OrderRepository;
import com.CabbageAndGarlic.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ShippingService {

    private final ShippingRepository shippingRepository;

    private final OrderService orderService;

    private final OrderRepository orderRepository;

    private final SmsService smsService;


    //체크박스에 체크한 정보를 컨트롤로에서 받고 레포지토리 호출 후 수정
    public void updateOrderStatus(List<Long> orderNumbers, String status) {
        List<Order> orders = orderRepository.findAllById(orderNumbers);
        for (Order order : orders) {
            order.setStatus(Status.valueOf(status));
        }
        orderRepository.saveAll(orders);
    }

    public void createShippings(List<ShippingDto> shippingRequests) throws IOException {
        for (ShippingDto request : shippingRequests) {
            Order order = orderRepository.findById(request.getOrderNumber())
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            List<OrderItem> orderItems = orderService.findOrderItemsByOrderNumber(request.getOrderNumber());

            Shipping shipping = new Shipping();
            shipping.setOrderNumber(order);
            shipping.setShippingCompany(request.getShippingCompany());
            shipping.setShippingDate(LocalDate.parse(request.getShippingDate()));
            shippingRepository.save(shipping);

            String orderItemsInfo = "";
            for(int i=0;i<orderItems.size();i++){
                orderItemsInfo += orderItems.get(i).getProductName() + " " +orderItems.get(i).getAmount()+"개\n";
            }
            String body = "수주번호 " + order.getOrderNumber() + " \n운송업체 " + request.getShippingCompany()+"\n"+ orderItemsInfo +" 맞짱뜨자 시발!";
            System.out.println(body);

            //문자전송은 반드시 꺼놓으세요
//            smsService.messageSend(order.getPhoneNumber(), body);    //문자 최종 전송 기본 30원 길면 50원이상
        }
    }
    // PendingShipment, SHIPPED 상태인 수주 정보만 조회
    public List<ShippingDto> findPendingAndShippedOrders() {
        List<Shipping> shippingItem = shippingRepository.findAllByOrderNumber_StatusIn(List.of(Status.PendingShipment, Status.SHIPPED));
//        System.out.println(shippingItem.size() + "개의 출하 정보가 있습니다."); // 디버깅용 출력
        return shippingItem.stream().map(shipping -> {
            ShippingDto dto = new ShippingDto();
            dto.setOrderNumber(shipping.getOrderNumber().getOrderNumber()); //get을 두번 사용하는 이유는 수주번호는 shipping안에 있는 order 필드이기 때문
            dto.setShippingCompany(shipping.getShippingCompany()); //운송업체 데이터
            dto.setShippingDate(shipping.getShippingDate().toString()); //출하일 데이터
            dto.setShippingStatus(shipping.getOrderNumber().getStatus().toString()); //출하 상태 데이터(조인한거)
            dto.setShippingClient(shipping.getOrderNumber().getClient()); //수주번호에 대한 수주처 데이터(조인한거)
            dto.setPhoneNumber(shipping.getOrderNumber().getPhoneNumber()); //수주번호에 대한 수주처 전화번호 데이터(조인한거)

            return dto;
        }).collect(Collectors.toList());
    }
}