package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
