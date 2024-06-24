package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.constant.Status;
import com.CabbageAndGarlic.dto.WorkOrderDto;
import com.CabbageAndGarlic.entity.ProductionPlan;
import com.CabbageAndGarlic.entity.WorkOrder;
import com.CabbageAndGarlic.repository.WorkOrderRepository;
import jakarta.persistence.EntityNotFoundException;
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
public class WorkOrderService {
    private final WorkOrderRepository workOrderRepository;

    public void saveWorkOrder(WorkOrderDto workOrderDto) {
        WorkOrder workOrder = new WorkOrder();
        workOrder.setWorkOrderNumber(workOrderDto.getWorkOrderNumber());
        workOrder.setProcess(workOrderDto.getProcess());
        workOrder.setProductType(workOrderDto.getProductType());
        workOrder.setWorkAmount(workOrderDto.getWorkAmount());
        workOrder.setWorker(workOrderDto.getWorker());
        workOrder.setWorkStatus(Status.WAITING);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. M. d.");
        LocalDate plandate = LocalDate.parse(workOrderDto.getOrderDate(), formatter);
        workOrder.setOrderDate(plandate);

        workOrderRepository.save(workOrder);
    }

    public List<WorkOrder> getWorkOrders(LocalDate date) {
        String dateTime= date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate orderDate = LocalDate.parse(dateTime, formatter);
        List<WorkOrder> workOrders = workOrderRepository.findByOrderDate(orderDate);
        if(workOrders == null) {
            throw new EntityNotFoundException("Production plan not found");
        }
        return workOrders;
    }

    public List<WorkOrder> getWorkOrders(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate orderDate = LocalDate.parse(date, formatter);
        List<WorkOrder> workOrders = workOrderRepository.findByOrderDate(orderDate);
        if(workOrders == null) {
            throw new EntityNotFoundException("Production plan not found");
        }
            return workOrders;
    }
}
