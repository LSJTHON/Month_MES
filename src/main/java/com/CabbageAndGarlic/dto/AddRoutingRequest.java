package com.CabbageAndGarlic.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddRoutingRequest {
    private String routingProductName;
    private List<String> processName;
}
