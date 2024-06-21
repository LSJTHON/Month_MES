package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.dto.RoutingListViewResponse;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.entity.Routing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoutingRepository extends JpaRepository<Routing, Long> {
    List<Routing> findByNumber(Long routingNumber);
}
