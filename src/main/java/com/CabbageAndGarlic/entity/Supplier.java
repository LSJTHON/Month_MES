package com.CabbageAndGarlic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "supplier") //발주처
public class Supplier {
    @Id
    @Column(name = "supplier_code")
    private String supplierCode;  // 발주처코드

    @Column(name = "supplier_name", nullable = false)
    private String supplierName; //발주처명

    @Column(name = "manager")
    private String manager;     //담당자

}

