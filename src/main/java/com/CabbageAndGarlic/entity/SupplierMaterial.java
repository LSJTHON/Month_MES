package com.CabbageAndGarlic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "supplier_material") //발주처랑 자재 연결
public class SupplierMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "supplier_code", nullable = false)
    private SupplierManage supplier;   //발주처코드

    @ManyToOne
    @JoinColumn(name = "material_code", nullable = false)
    private Material material;  //자재코드

    @Column(name = "min_amount", nullable = false)
    private int minAmount;  //최소주문수량

    @Column(name = "max_amount", nullable = false)
    private int maxAmount;  //최대주문수량

    @Column(name = "unit_price", nullable = false)
    private int unitPrice;  //단가
}
