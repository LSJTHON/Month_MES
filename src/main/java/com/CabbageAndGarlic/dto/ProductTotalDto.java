package com.CabbageAndGarlic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductTotalDto {
    private String productName;
    private Integer totalAmount;
}

