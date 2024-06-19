package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.ProductionPlan;
import com.CabbageAndGarlic.entity.ProductionPlanOrderMap;
import com.CabbageAndGarlic.repository.ProductionPlanOrderMapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {
    private final ProductionPlanOrderMapRepository productionPlanOrderMapRepository;

    public List<Order> getOrder(ProductionPlan productionPlan) {
        List<ProductionPlanOrderMap> productionPlanOrderMaps = productionPlanOrderMapRepository.findProductionPlanOrderMapByProductionPlanNumber(productionPlan);
        List<Order> order = null;
        for(ProductionPlanOrderMap productionPlanOrderMap : productionPlanOrderMaps) {
            order.add(productionPlanOrderMap.getOrderNumber());
        }
        return order;
    }
}
