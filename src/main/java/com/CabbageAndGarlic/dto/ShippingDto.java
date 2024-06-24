package com.CabbageAndGarlic.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingDto { //출하 요청 시 사용할 DTO
    private Long orderNumber;
    private String shippingCompany;
    private String shippingDate;
    private String shippingStatus;
    private String shippingClient;
    private String phoneNumber;
}
