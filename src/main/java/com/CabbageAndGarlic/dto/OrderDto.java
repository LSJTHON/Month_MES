package com.CabbageAndGarlic.dto;


import com.CabbageAndGarlic.constant.Status;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private Long orderNumber;
    private String client;
    private Status status;
    private LocalDate deliveryDate;
    private String phoneNumber;
    private String manager;
    private List<OrderItemDto> orderItems;
}