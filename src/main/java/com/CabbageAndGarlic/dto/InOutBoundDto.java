package com.CabbageAndGarlic.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class InOutBoundDto {
    private String materialCode; // 자재 코드
    private String productCode;  // 제품 코드
    private String materialName; // 자재명
    private String productName;  // 제품명
    private LocalDateTime transactionDate; // 입출고일
    private String transactionType; // 입출고 타입 (IN, OUT)
    private int quantity;  // 수량
    private String manager;  // 담당자
}
