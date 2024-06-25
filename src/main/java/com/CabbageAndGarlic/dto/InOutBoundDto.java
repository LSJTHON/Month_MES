package com.CabbageAndGarlic.dto;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter @Setter
public class InOutBoundDto {

//    private long purchaseNumber; //발주번호
//    private String supplierName;  // 발주처

    private String materialName; //자재명

    private LocalDateTime transactionDate; // 입출고일

    private String transactionType; // 입출고 타입 (IN, OUT)

    private int quantity;  // 수량

    private String manager;  // 담당자 : 고정?
}
