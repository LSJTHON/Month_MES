package com.CabbageAndGarlic.dto;

import com.CabbageAndGarlic.entity.InOutBound;
import com.CabbageAndGarlic.entity.PurchaseOrder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter @Setter
public class InOutBoundDto {

    private long purchaseNumber; //발주번호

    private String MaterialName; //자재명

    private LocalDateTime inboundDate; //입고일

    private LocalDateTime outboundDate;  // 출고일

    private String location;  // 위치

    private int quantity;  // 수량

    private String supplierName;  // 발주처

    private String managerName;  // 담당자
}
