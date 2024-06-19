package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.ProductionPlan;
import com.CabbageAndGarlic.entity.ProductionPlanOrderMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ProductionPlanRepository extends JpaRepository<ProductionPlan, Long> {
    ProductionPlan findProductionPlanByplanDate(LocalDateTime planDate);
}
