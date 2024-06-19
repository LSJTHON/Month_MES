package com.CabbageAndGarlic.entity;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "supplier_manager") //발주처
public class SupplierManager {
    @Id
    @Column(name = "supplier_code")
    private String supplierCode;  // 발주처코드

    @Column(name = "supplier_name")
    private String supplierName; //발주처명

    @Column(name = "manager")
    private String manager;     //담당자

}

