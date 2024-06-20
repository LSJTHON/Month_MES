package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.entity.ProductionPlan;
import com.CabbageAndGarlic.repository.ProductionPlanRepository;
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

    //날짜에 해당하는 생산계획일 찾는
    public List<ProductionPlan> findProductionPlan(LocalDateTime date) {
        String dateTime= date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime plandate = LocalDate.parse(dateTime, formatter).atStartOfDay();
        List<ProductionPlan> productionPlans = productionPlanRepository.findByPlanDate(plandate);
        if(productionPlans == null) {
            throw new EntityNotFoundException("Production plan not found");
        }

        return productionPlans;
    }

    public List<ProductionPlan> findProductionPlan(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime plandate = LocalDate.parse(date, formatter).atStartOfDay();
        List<ProductionPlan> productionPlans = productionPlanRepository.findByPlanDate(plandate);
        if(productionPlans == null) {
            throw new EntityNotFoundException("Production plan not found");
        }
        return productionPlans;
    }

}
