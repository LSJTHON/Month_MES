package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.constant.Status;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.Shipping;
import com.CabbageAndGarlic.repository.OrderRepository;
import com.CabbageAndGarlic.repository.ShippingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ShippingStatusUpdater {

    private final ShippingRepository shippingRepository;
    private final OrderRepository orderRepository;

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정에 실행
    public void updateShippingStatus() {
        updateShippingStatusLogic();
    }

    @EventListener(ApplicationReadyEvent.class) // 애플리케이션 시작 시 실행
    public void onApplicationReady() {
        updateShippingStatusLogic();
    }

    private void updateShippingStatusLogic() {
        LocalDate twoDaysAgo = LocalDate.now().minusDays(2);
        List<Shipping> shipments = shippingRepository.findByShippingDateBefore(twoDaysAgo);

        for (Shipping shipment : shipments) {
            Order order = shipment.getOrderNumber();
            if (order.getStatus() == Status.PendingShipment) {
                order.setStatus(Status.SHIPPED);
                orderRepository.save(order);
            }
        }
    }
}
