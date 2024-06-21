package com.CabbageAndGarlic.dto;

import com.CabbageAndGarlic.entity.ProcessManagement;
import com.CabbageAndGarlic.entity.Product;
import com.CabbageAndGarlic.entity.Routing;
import lombok.Getter;

@Getter
public class RoutingListViewResponse {
    private final int number;
    private final ProcessManagement routingNumber;
    private final ProcessManagement routingProduct;  // 공정명
    private final ProcessManagement cycleHour;  // 작업시간
    private final Product routingProductName;  // 품목명
    private final Integer allCycleTime;  // 전체공정 시간

    public RoutingListViewResponse(Routing routing) {
        this.number = routing.getNumber();
        this.routingNumber = routing.getRoutingNumber();
        this.routingProduct = routing.getRoutingProduct();
        this.cycleHour = routing.getCycleHour();
        this.routingProductName = routing.getRoutingProductName();
        this.allCycleTime = routing.getAllCycleTime();
    }
}
