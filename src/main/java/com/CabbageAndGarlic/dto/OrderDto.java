package com.CabbageAndGarlic.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private Long orderNumber;
    private String client;
    private String status;
    private LocalDateTime deliveryDate;
    private String phoneNumber;
    private String manager;
    private List<OrderItemDto> orderItems;
}