package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE order_table SET order_status = 'COMPLETED' WHERE order_number = :orderNumber", nativeQuery = true)
    int updateOrderStatusToCompleted(@Param("orderNumber") Long orderNumber);

}
