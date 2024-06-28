package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.constant.Status;
import com.CabbageAndGarlic.dto.ProductionDto;
import com.CabbageAndGarlic.dto.WorkOrderDto;
import com.CabbageAndGarlic.entity.*;
import com.CabbageAndGarlic.repository.*;
import com.CabbageAndGarlic.service.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
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
    private final OrderItemRepository orderItemRepository;
    private final OrderProductionPlanRepository orderProductionPlanRepository;
    private final OrderRepository orderRepository;
    private final WorkOrderRepository workOrderRepository;
    private final ProductionPlanRepository productionPlanRepository;


    @GetMapping(value = "/today")
    public ResponseEntity<?> productionOrders() throws ParseException {
        Map<String, Object> response = new HashMap<>();

        List<ProductionDto> productionDtos = new ArrayList<>();
        LocalDate date = LocalDate.now();
        try {
            ProductionPlan productionPlan = productionPlanService.findProductionPlan(date);
            if (productionPlan == null) {
                throw new EntityNotFoundException("Production plan not found for date: " + date);
            }

            List<OrderProductionPlan> orderProductionPlans = orderProductionPlanRepository.findByProductionPlan(productionPlan);
            for (OrderProductionPlan orderProductionPlan : orderProductionPlans) {
                Order order = orderProductionPlan.getOrderNumber();
                List<OrderItem> orderItems = orderItemRepository.findByOrderNumber(order);
                for (OrderItem orderItem : orderItems) {
                    ProductionDto productionDto = new ProductionDto(
                            orderItem.getProductName(),
                            orderItem.getStartDate(),
                            orderItem.getEndDate(),
                            orderItem.getAmount(),
                            orderItem.getStatus()
                    );
                    productionDtos.add(productionDto);
                }
            }

            // 집계된 ProductionDto 리스트를 저장할 맵
            Map<String, ProductionDto> aggregatedMap = new HashMap<>();

            for (ProductionDto productionDto : productionDtos) {
                String productName = productionDto.getProductName();
                if (aggregatedMap.containsKey(productName)) {
                    // 기존 ProductionDto에 양을 추가
                    ProductionDto existingDto = aggregatedMap.get(productName);
                    existingDto.setAmount(existingDto.getAmount() + productionDto.getAmount());
                } else {
                    // 새로운 제품명을 맵에 추가
                    aggregatedMap.put(productName, productionDto);
                }
            }

            // 집계된 결과를 리스트로 변환
            List<ProductionDto> aggregatedProductionDtos = new ArrayList<>(aggregatedMap.values());

            response.put("data", aggregatedProductionDtos);
            return ResponseEntity.ok(response);

        } catch (EntityNotFoundException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("error", "An error occurred while processing the request.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping(value = "/{date}")
    public ResponseEntity<?> productionOrders(@PathVariable String date) throws ParseException {
        Map<String, Object> response = new HashMap<>();
        List<ProductionDto> productionDtos = new ArrayList<>();
        try {
            ProductionPlan productionPlan = productionPlanService.findProductionPlan(date);
            if (productionPlan == null) {
                throw new EntityNotFoundException("Production plan not found for date: " + date);
            }

            List<OrderProductionPlan> orderProductionPlans = orderProductionPlanRepository.findByProductionPlan(productionPlan);
            for (OrderProductionPlan orderProductionPlan : orderProductionPlans) {
                Order order = orderProductionPlan.getOrderNumber();
                List<OrderItem> orderItems = orderItemRepository.findByOrderNumber(order);
                for (OrderItem orderItem : orderItems) {
                    ProductionDto productionDto = new ProductionDto(
                            orderItem.getProductName(),
                            orderItem.getStartDate(),
                            orderItem.getEndDate(),
                            orderItem.getAmount(),
                            orderItem.getStatus()
                    );
                    productionDtos.add(productionDto);
                }
            }

            // 집계된 ProductionDto 리스트를 저장할 맵
            Map<String, ProductionDto> aggregatedMap = new HashMap<>();

            for (ProductionDto productionDto : productionDtos) {
                String productName = productionDto.getProductName();
                if (aggregatedMap.containsKey(productName)) {
                    // 기존 ProductionDto에 양을 추가
                    ProductionDto existingDto = aggregatedMap.get(productName);
                    existingDto.setAmount(existingDto.getAmount() + productionDto.getAmount());
                } else {
                    // 새로운 제품명을 맵에 추가
                    aggregatedMap.put(productName, productionDto);
                }
            }

            // 집계된 결과를 리스트로 변환
            List<ProductionDto> aggregatedProductionDtos = new ArrayList<>(aggregatedMap.values());

            response.put("data", aggregatedProductionDtos);
            return ResponseEntity.ok(response);

        } catch (EntityNotFoundException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (Exception e) {
            response.put("error", "An error occurred while processing the request.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
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
        response.put("data", workOrders);

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/workOrder/{date}")
    public ResponseEntity<?> workOrders(@PathVariable String date) throws ParseException {
        Map<String, Object> response = new HashMap<>();
        List<WorkOrder> workOrders = workOrderService.getWorkOrders(date);
        response.put("data", workOrders);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/workStart")
    public String startWork(@RequestBody Long workOrderNumber) throws ParseException {
        WorkOrder workOrder = workOrderService.startWorkOrder(workOrderNumber);
        List<OrderProductionPlan> orderProductionPlans = orderProductionPlanRepository.findByProductionPlan(productionPlanRepository.findByPlanDate(workOrder.getOrderDate()));
        for (OrderProductionPlan orderProductionPlan : orderProductionPlans) {
            Order order = orderProductionPlan.getOrderNumber();
            List<OrderItem> orderItems = orderItemRepository.findByOrderNumber(order);
            for(OrderItem orderItem : orderItems) {
                if(orderItem.getProductName().equals(workOrder.getProductName())){
                    if(workOrder.getProcess().equals("전처리")||workOrder.getProcess().equals("혼합")){
                        orderItem.setStartDate(LocalDateTime.now());
                        orderItem.setStatus(Status.IN_PROGRESS);
                        orderItemRepository.save(orderItem);
                        order.setStatus(Status.IN_PROGRESS);
                        orderRepository.save(order);
                    }
                }
            }
        }
        return "작업시작";
    }

    @PostMapping(value = "/workEnd")
    public String endWork(@RequestBody Long workOrderNumber) throws ParseException {
        WorkOrder workOrder = workOrderService.endWorkOrder(workOrderNumber);
        workOrder.setStartTimeOfOperation(LocalDateTime.now());
        workOrderRepository.save(workOrder);
        List<OrderProductionPlan> orderProductionPlans = orderProductionPlanRepository.findByProductionPlan(productionPlanRepository.findByPlanDate(workOrder.getOrderDate()));
        for (OrderProductionPlan orderProductionPlan : orderProductionPlans) {
            List<OrderItem> orderItems = orderItemRepository.findByOrderNumber(orderProductionPlan.getOrderNumber());
            for(OrderItem orderItem : orderItems) {
                if(orderItem.getProductName().equals(workOrder.getProductName())){
                    if(workOrder.getProcess().equals("포장")){
                        orderItem.setEndDate(LocalDateTime.now());
                        orderItem.setStatus(Status.COMPLETED);
                        orderItemRepository.save(orderItem);
                    }
                }
            }
        }
        return "작업종료";
    }

}
