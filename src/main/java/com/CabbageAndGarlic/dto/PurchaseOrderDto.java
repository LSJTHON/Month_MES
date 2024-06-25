package com.CabbageAndGarlic.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PurchaseOrderDto {

    private Long purchaseNumber; // 발주번호

    private String purchaseOrderStatus; // 발주상태

    private Long supplierManageId; // SupplierManage ID

    private Long orderNumber; // 수주번호

    private int amount; // 발주수량

    private LocalDateTime receiptDate; // 입고일

    private LocalDateTime purchaseDate; // 발주일
}
