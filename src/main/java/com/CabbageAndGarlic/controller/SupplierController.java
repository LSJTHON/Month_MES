package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.entity.PurchaseOrder;
import com.CabbageAndGarlic.entity.SupplierManager;
import org.springframework.web.bind.annotation.GetMapping;

public class SupplierController {

    @GetMapping(value = "/supplier")
    public String supplier(SupplierManager supplierManager){

        return "Supplier/Supplier";
    }
}
