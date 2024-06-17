package com.CabbageAndGarlic.dto;


import lombok.*;

@Getter
@Setter
public class OrderItemDto {
    private String productName;
    private Integer amount;
}