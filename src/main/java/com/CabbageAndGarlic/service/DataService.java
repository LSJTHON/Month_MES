package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.dto.ProductionDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.entity.ProductionPlan;
import com.CabbageAndGarlic.repository.OrderItemRepository;
import com.CabbageAndGarlic.repository.OrderRepository;
import com.CabbageAndGarlic.repository.ProductionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DataService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductionPlanRepository productionPlanRepository;

//    public List<ProductionDto> getAllData(List<Order> orders) {
//        List<OrderItem> orderItems = orderItemRepository.findAll();
//        List<ProductionPlan> productionPlans = productionPlanRepository.findAll();
//
//        return orders.stream().flatMap(order -> {
//            List<OrderItem> items = orderItems.stream()
//                    .filter(item -> item.getOrderNumber().getOrderNumber().equals(order.getOrderNumber()))
//                    .collect(Collectors.toList());
//
//            return items.stream().map(item -> {
//                ProductionPlan plan = productionPlans.stream()
//                        .filter(pp -> pp.getProductionPlanNumber().equals(item.getOrderNumber().getOrderNumber()))
//                        .findFirst().orElse(null);
//
//                return new ProductionDto(
//                        order.getOrderNumber(),
//                        item.getProductName(),
//                        order.getClient(),
//                        plan != null ? plan.getStartDate() : null,
//                        plan != null ? plan.getEndDate() : null,
//                        item.getAmount(),
//                        plan != null ? plan.getProductionPlanStatus() : null
//                );
//            });
//        }).collect(Collectors.toList());
//    }
}
