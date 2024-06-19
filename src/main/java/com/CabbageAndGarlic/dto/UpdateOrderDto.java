package com.CabbageAndGarlic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateOrderDto {  //수주에 대한 출하 시 상태변경 DTO
    private List<Long> orderNumbers;
    private String status;
}
