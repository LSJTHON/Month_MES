package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.entity.PurchaseOrder;
import org.springframework.web.bind.annotation.GetMapping;


public class PurchaseOrderController {

    @GetMapping(value = "/purchase")
    public String purchase(PurchaseOrder purchaseOrder){

    return "Purchase/Purchase";
    }
}
