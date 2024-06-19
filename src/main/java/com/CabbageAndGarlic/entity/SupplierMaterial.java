package com.CabbageAndGarlic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "supplierMaterial") //발주처랑 자재 연결
public class SupplierMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_code", nullable = false)
    private SupplierManager supplierCode;

    @ManyToOne
    @JoinColumn(name = "material_code", nullable = false)
    private Material materialCode;

    @Column(name = "min_amount", nullable = false)
    private int minAmount;

    @Column(name = "max_amount", nullable = false)
    private int maxAmount;

    @Column(name = "unit_price", nullable = false)
    private int unitPrice;
}
