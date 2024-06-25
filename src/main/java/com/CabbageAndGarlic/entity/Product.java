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
    @Column(name = "product_code")
    private String productCode;  // 품목코드
    //품목 코드는 알파벳을 섞을 것인가?

    @Column(name = "product_name", nullable = false)
    private String productName;  // 품목명


    @Column(name = "max_amount", nullable = false)
    private Integer maxAmount;  // 최대수량

    @Column(name = "min_amount", nullable = false)
    private Integer minAmount;  // 최대수량

    @Column(name = "product_amount", nullable = false)
    private Integer productAmount;  // 완제품 양

    @Column(name = "bom")
    private String bom;  // BOM

    @Builder
    public Product(String productCode, String productName, int maxAmount, int minAmount, String bom, int productAmount) {
        this.productCode = productCode;
        this.productName = productName;
        this.maxAmount = maxAmount;
        this.minAmount = minAmount;
        this.productAmount = productAmount;
        this.bom = bom;
    }

}
