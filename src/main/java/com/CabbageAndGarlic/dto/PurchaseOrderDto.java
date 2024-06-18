package com.CabbageAndGarlic.dto;

import com.CabbageAndGarlic.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class PurchaseOrderDto {

    private long purchaseNumber; //발주번호

    private String materialCode; //자재코드

    private String materialName; //자재명

    private String supplierCode; //발주처코드

    private String supplierName; //발주처명

    private int amount; //발주수량

    private LocalDateTime order_date; //발주일

    private LocalDateTime receipt_date; //입고일

    private String purchaseOrderStatus; //발주상태

    private String manager; //담당자

}
