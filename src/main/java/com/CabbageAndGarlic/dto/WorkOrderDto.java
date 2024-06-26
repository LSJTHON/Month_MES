package com.CabbageAndGarlic.dto;

import com.CabbageAndGarlic.constant.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class WorkOrderDto {
    Long workOrderNumber;
    String process;
    Integer workAmount;
    String worker;
    String orderDate;
    String productName;
    Long orderNumber;
}


