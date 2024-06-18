package com.CabbageAndGarlic.dto;


import lombok.*;

@Getter
@Setter
public class OrderItemDto {
    private String productName;
    private Integer amount;

    // 생성자
    public OrderItemDto(String productName, Integer amount) {
        this.productName = productName;
        this.amount = amount;
    }
}
