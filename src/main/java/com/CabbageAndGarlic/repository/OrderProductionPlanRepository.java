package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.OrderProductionPlan;
import com.CabbageAndGarlic.entity.ProductionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderProductionPlanRepository extends JpaRepository<OrderProductionPlan, Long> {
    List<OrderProductionPlan> findByProductionPlan(ProductionPlan productionPlan);
}
