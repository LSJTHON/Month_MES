package com.CabbageAndGarlic.dto;

import com.CabbageAndGarlic.entity.ProcessManagement;
import com.CabbageAndGarlic.entity.Product;
import com.CabbageAndGarlic.entity.Routing;
import lombok.Getter;

@Getter
public class RoutingListViewResponse {
    private final int number;
    private final ProcessManagement routingNumber;
    private final Product routingProductName;
    private final Integer allCycleTime;

    public RoutingListViewResponse(Routing routing) {
        this.number = routing.getNumber();
        this.routingNumber = routing.getRoutingNumber();
        this.routingProductName = routing.getRoutingProductName();
        this.allCycleTime = routing.getAllCycleTime();
    }
}
