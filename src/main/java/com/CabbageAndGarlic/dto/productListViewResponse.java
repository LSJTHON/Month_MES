package com.CabbageAndGarlic.dto;

import com.CabbageAndGarlic.entity.Product;
import lombok.Getter;

@Getter
public class productListViewResponse {
    private final String productCode;
    private final String productName;
    private final int maxAmount;
    private final int minAmount;
    private final int unitPrice;
    private final int sellingPrice;
    private final String bom;

    public productListViewResponse(Product product) {
        this.productCode = product.getProductCode();
        this.productName = product.getProductName();
        this.maxAmount = product.getMaxAmount();
        this.minAmount = product.getMinAmount();
        this.unitPrice = product.getUnitPrice();
        this.sellingPrice = product.getSellingPrice();
        this.bom = product.getBom();
    }


}
