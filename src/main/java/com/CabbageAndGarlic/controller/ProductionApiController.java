package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.ProductionDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.entity.ProductionPlan;
import com.CabbageAndGarlic.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/production")
@RequiredArgsConstructor
public class ProductionApiController {
    private final ProductionPlanService productionPlanService;
    private final OrderService orderService;
    private final DataService dataService;

    @GetMapping(value = "/today")
    public ResponseEntity<?> productionOrders() throws ParseException {
        Map<String, Object> response = new HashMap<>();
        List<ProductionDto> productionDtos = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();
        List<ProductionPlan> productionPlans = productionPlanService.findProductionPlan(date);
        for (ProductionPlan productionPlan : productionPlans) {
            Order order = orderService.getOrder(productionPlan);
                List<OrderItem> items = orderService.findOrderItemsByOrderNumber(order.getOrderNumber());
                for(OrderItem item : items) {
                    ProductionDto productionDto = new ProductionDto(order.getOrderNumber(), item.getProductName(),
                            order.getClient(),item.getStartDate(),item.getEndDate(),item.getAmount(),order.getStatus());
                    productionDtos.add(productionDto);
                }
        }
        response.put("data", productionDtos);
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/{date}")
    public ResponseEntity<?> productionOrders(@PathVariable String date) throws ParseException {
        Map<String, Object> response = new HashMap<>();
        List<ProductionDto> productionDtos = new ArrayList<>();
        List<ProductionPlan> productionPlans = productionPlanService.findProductionPlan(date);
        for(ProductionPlan productionPlan : productionPlans) {
            Order order = orderService.getOrder(productionPlan);
                List<OrderItem> items = orderService.findOrderItemsByOrderNumber(order.getOrderNumber());
                for(OrderItem item : items) {
                    ProductionDto productionDto = new ProductionDto(order.getOrderNumber(), item.getProductName(),
                            order.getClient(), item.getStartDate(), item.getEndDate(), item.getAmount(), order.getStatus());
                    productionDtos.add(productionDto);
                }
            }
        response.put("data", productionDtos);
        return ResponseEntity.ok(response);
    }

}
