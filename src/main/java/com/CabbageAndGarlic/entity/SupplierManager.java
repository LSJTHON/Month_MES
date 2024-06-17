package com.CabbageAndGarlic.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "SupplierManager")
public class SupplierManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_manage_id")
    private Long supplierManageId;  // 관리번호

    @Column(name = "product_code", nullable = false)
    private Long productCode;  // 자재코드

    @Column(name = "product_name", nullable = false)
    private String productName;  // 자재명

    @Column(name = "min_amount", nullable = false)
    private Integer minAmount;  // 최소수량

    @Column(name = "max_amount", nullable = false)
    private Integer maxAmount;  // 최대수량

    @Column(name = "unit_price", nullable = false)
    private Integer unitPrice;  // 단가
}

