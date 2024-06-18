package com.CabbageAndGarlic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @Column(name="product_code")
    private String productCode;  // 품목코드
    //품목 코드는 알파벳을 섞을 것인가?

    @Column(name = "product_name", nullable = false)
    private String productName;  // 품목명


    @Column(name = "max_amount", nullable = false)
    private Integer maxAmount;  // 최대수량

    @Column(name = "unit_price", nullable = false)
    private Integer unitPrice;  // 단가

    @Column(name = "selling_price", nullable = false)
    private Integer sellingPrice;  // 판매가

    @Column(name = "bom")
    private String bom;  // BOM

    @Builder
    public Product(String productCode, String productName, int maxAmount, int unitPrice, int sellingPrice, String bom) {
        this.productCode = productCode;
        this.productName = productName;
        this.maxAmount = maxAmount;
        this.unitPrice = unitPrice;
        this.sellingPrice = sellingPrice;
        this.bom = bom;
    }

}
