package com.CabbageAndGarlic.dto;

import com.CabbageAndGarlic.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddProductRequest {
    private  String productCode;
    private  String productName;
    private  int maxAmount;
    private  int minAmount;
    private  String bom;
    public Product toEntity(){
        return Product.builder()
                .productCode(productCode)
                .productName(productName)
                .maxAmount(maxAmount)
                .minAmount(minAmount)
                .bom(bom)
                .build();
    }

}
