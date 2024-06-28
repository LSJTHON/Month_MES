package com.CabbageAndGarlic.dto;

import com.CabbageAndGarlic.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkOrderStatusDto {
    String process;
    Integer workAmount;
    Status status;
    Integer quantityLeft;
    String productName;
}
