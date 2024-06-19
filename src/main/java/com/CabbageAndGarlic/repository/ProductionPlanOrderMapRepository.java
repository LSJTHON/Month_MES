package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.ProductionPlan;
import com.CabbageAndGarlic.entity.ProductionPlanOrderMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionPlanOrderMapRepository extends JpaRepository<ProductionPlanOrderMap, Long> {
    List<ProductionPlanOrderMap> findProductionPlanOrderMapByProductionPlanNumber(ProductionPlan productionPlan);
}
