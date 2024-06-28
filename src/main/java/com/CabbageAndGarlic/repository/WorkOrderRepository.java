package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
    List<WorkOrder> findByOrderDate(LocalDate date);
    WorkOrder findByWorkOrderNumber(Long workOrderId);
}
