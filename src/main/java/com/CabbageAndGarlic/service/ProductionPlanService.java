package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.entity.ProductionPlan;
import com.CabbageAndGarlic.repository.ProductionPlanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public ProductionPlan findProductionPlan(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime plandate = LocalDate.parse(date, formatter).atStartOfDay();
        ProductionPlan productionPlan = productionPlanRepository.findProductionPlanByplanDate(plandate);
        return productionPlan;
    }
}
