package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
