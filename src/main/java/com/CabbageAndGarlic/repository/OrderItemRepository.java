package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    List<OrderItem> findByOrderNumber(Order orderNumber);

    List<OrderItem> findByOrderNumberIn(List<Order> orderNumbers);

}
