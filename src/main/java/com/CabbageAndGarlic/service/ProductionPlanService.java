package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.constant.Status;
import com.CabbageAndGarlic.dto.WorkOrderDto;
import com.CabbageAndGarlic.entity.ProductionPlan;
import com.CabbageAndGarlic.entity.WorkOrder;
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
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductionPlanService {
    private final ProductionPlanRepository productionPlanRepository;
    private final WorkOrderRepository workOrderRepository;

    //날짜에 해당하는 생산계획일 찾는
    public List<ProductionPlan> findProductionPlan(LocalDate date) {
        String dateTime= date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate plandate = LocalDate.parse(dateTime, formatter);
        List<ProductionPlan> productionPlans = productionPlanRepository.findByPlanDate(plandate);
        if(productionPlans == null) {
            throw new EntityNotFoundException("Production plan not found");
        }

        return productionPlans;
    }

    public List<ProductionPlan> findProductionPlan(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate plandate = LocalDate.parse(date, formatter);
        List<ProductionPlan> productionPlans = productionPlanRepository.findByPlanDate(plandate);
        if(productionPlans == null) {
            throw new EntityNotFoundException("Production plan not found");
        }
        return productionPlans;
    }


}
