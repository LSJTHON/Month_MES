package com.CabbageAndGarlic.dto;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseOrderRequest {

    private List<MaterialRequirement> materialRequirements;
    private List<Long> orderNumbers;

    @Data
    public static class MaterialRequirement {
        private String materialCode;
        private double totalAmount;
    }
}
