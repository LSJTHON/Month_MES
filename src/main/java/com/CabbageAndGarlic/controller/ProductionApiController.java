package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.ProductionDto;
import com.CabbageAndGarlic.dto.WorkOrderDto;
import com.CabbageAndGarlic.entity.Order;
import com.CabbageAndGarlic.entity.OrderItem;
import com.CabbageAndGarlic.entity.ProductionPlan;
import com.CabbageAndGarlic.entity.WorkOrder;
import com.CabbageAndGarlic.repository.WorkOrderRepository;
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
    private final WorkOrderService workOrderService;


    @GetMapping(value = "/today")
    public ResponseEntity<?> productionOrders() throws ParseException {
        Map<String, Object> response = new HashMap<>();
        List<ProductionDto> productionDtos = new ArrayList<>();
        LocalDate date = LocalDate.now();
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

    @PostMapping(value = "/createWork")
    public String createWork(@RequestBody WorkOrderDto workOrderDto) throws ParseException {
        System.out.println(workOrderDto);
        workOrderService.saveWorkOrder(workOrderDto);
        return "등록 완료";
    }

    @GetMapping(value = "/workOrder/today")
    public ResponseEntity<?> workOrders() throws ParseException {
        Map<String, Object> response = new HashMap<>();
        LocalDate date = LocalDate.now();
        List<WorkOrder> workOrders = workOrderService.getWorkOrders(date);
        System.out.println(workOrders);
        response.put("data", workOrders);

        return ResponseEntity.ok(response);
    }

}
