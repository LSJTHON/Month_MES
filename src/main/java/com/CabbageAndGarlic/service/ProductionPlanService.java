package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.constant.Status;
import com.CabbageAndGarlic.dto.OrderDto;
import com.CabbageAndGarlic.dto.OrderItemDto;
import com.CabbageAndGarlic.dto.WorkOrderDto;
import com.CabbageAndGarlic.entity.*;
import com.CabbageAndGarlic.repository.OrderProductionPlanRepository;
import com.CabbageAndGarlic.repository.OrderRepository;
import com.CabbageAndGarlic.repository.ProductionPlanRepository;
import com.CabbageAndGarlic.repository.WorkOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductionPlanService {
    private final ProductionPlanRepository productionPlanRepository;
    private final OrderRepository orderRepository;
    private final OrderProductionPlanRepository orderProductionPlanRepository;

    public void savePlan(OrderDto orderDto) {
        LocalDate orderDay = orderDto.getDeliveryDate();
        LocalDate planday = orderDay.minusDays(2);
        if(productionPlanRepository.findByPlanDate(planday)==null){
            ProductionPlan plan = new ProductionPlan();
            plan.setPlanDate(planday);
            plan.setProductionPlanStatus(Status.WAITING);
            plan.setProductionPlanNumber(orderDto.getOrderNumber()-10);
            productionPlanRepository.save(plan);
            OrderProductionPlan orderProductionPlan = new OrderProductionPlan();
            orderProductionPlan.setOrderNumber(orderRepository.findByOrderNumber(orderDto.getOrderNumber()));
            orderProductionPlan.setProductionPlan(plan);
            orderProductionPlanRepository.save(orderProductionPlan);

        }else {
            OrderProductionPlan orderProductionPlan = new OrderProductionPlan();
            orderProductionPlan.setOrderNumber(orderRepository.findByOrderNumber(orderDto.getOrderNumber()));
            orderProductionPlan.setProductionPlan(productionPlanRepository.findByPlanDate(planday));
            orderProductionPlanRepository.save(orderProductionPlan);
        }

    }

    //날짜에 해당하는 생산계획일 찾는
    public ProductionPlan findProductionPlan(LocalDate date) {
        String dateTime= date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate plandate = LocalDate.parse(dateTime, formatter);
        ProductionPlan productionPlan = productionPlanRepository.findByPlanDate(plandate);
        if(productionPlan == null) {
            throw new EntityNotFoundException("Production plan not found");
        }

        return productionPlan;
    }

    public ProductionPlan findProductionPlan(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate plandate = LocalDate.parse(date, formatter);
        ProductionPlan productionPlan = productionPlanRepository.findByPlanDate(plandate);
        if(productionPlan == null) {
            throw new EntityNotFoundException("Production plan not found");
        }
        return productionPlan;
    }



}
