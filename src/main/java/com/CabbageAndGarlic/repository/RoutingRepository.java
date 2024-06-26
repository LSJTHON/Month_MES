package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.Routing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoutingRepository extends JpaRepository<Routing, Integer> {
    List<Routing> findByNumber(int routingNumber);

    @Query("SELECT r FROM Routing r WHERE r.routingProductName.productName = :productName")
    List<Routing> findByProductName(String productName);
}