package com.CabbageAndGarlic.dto;

import com.CabbageAndGarlic.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ProductionDto {
    private String productName;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer amount;
    private Status status;
}
