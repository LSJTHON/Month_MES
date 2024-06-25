package com.CabbageAndGarlic.dto;

import com.CabbageAndGarlic.entity.ProcessManagement;
import com.CabbageAndGarlic.entity.Product;
import com.CabbageAndGarlic.entity.Routing;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddRoutingRequest {
    private Integer number;
    private ProcessManagement routingNumber;  // 공정 번호
    private Product routingProductName;  // 품목명
    private Integer allCycleTime;  // 전체공정 시간

    public Routing toEntity() {
        return Routing.builder()
                .number(number)
                .routingNumber(routingNumber)
                .routingProductName(routingProductName)
                .allCycleTime(allCycleTime)
                .build();
    }
}
