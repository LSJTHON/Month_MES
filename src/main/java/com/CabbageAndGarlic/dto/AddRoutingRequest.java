package com.CabbageAndGarlic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddRoutingRequest {
    private String routingProductName; //자바 스크릅트에서 정보 저장용 DTO
    private List<String> processName;
}
