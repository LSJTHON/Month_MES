package com.CabbageAndGarlic.dto;

import com.CabbageAndGarlic.entity.Product;
import lombok.Getter;

@Getter
public class ProductListViewResponse {
    private final String productCode;
    private final String productName;
    private final int maxAmount;
    private final int minAmount;
    private final int productAmount;
    private final String bom;

    public ProductListViewResponse(Product product) {
        this.productCode = product.getProductCode();
        this.productName = product.getProductName();
        this.maxAmount = product.getMaxAmount();
        this.minAmount = product.getMinAmount();
        this.productAmount = product.getProductAmount();
        this.bom = product.getBom();
    }


}
