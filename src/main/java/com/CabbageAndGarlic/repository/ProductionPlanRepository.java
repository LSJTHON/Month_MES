package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.ProductionPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ProductionPlanRepository extends JpaRepository<ProductionPlan, Long> {
    List<ProductionPlan> findByPlanDate(LocalDate planDate);
}
