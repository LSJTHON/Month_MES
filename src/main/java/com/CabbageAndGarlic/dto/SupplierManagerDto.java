package com.CabbageAndGarlic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SupplierManagerDto {

    private String supplierCode; //발주처코드

    private String supplierName; //발주처명

    private String materialCode; //자재코드

    private String materialName; //자재명

    private int minAmount; //최소주문수량

    private int maxAmount; //최대주문수량

    private int unitPrice; //단가


}
