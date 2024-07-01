package com.CabbageAndGarlic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class PurchaseOrderDto {
    private Long purchaseNumber; // 발주번호
    private String supplierName; // 발주처
    private String materialName; // 자재명
    private int amount; // 수량
    private LocalDateTime purchaseDate; // 발주일
    private LocalDateTime receiptDate; // 입고일
    private String purchaseOrderStatus; // 발주상태
    private String manager; // 담당자

}
