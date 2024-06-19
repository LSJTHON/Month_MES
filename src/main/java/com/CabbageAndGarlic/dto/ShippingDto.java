package com.CabbageAndGarlic.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ShippingDto { //출하 요청 시 사용할 DTO
    private Long orderNumber;
    private String shippingCompany;
    private String shippingDate;
}
