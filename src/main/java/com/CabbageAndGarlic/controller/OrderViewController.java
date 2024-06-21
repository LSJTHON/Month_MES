package com.CabbageAndGarlic.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderViewController {

    @GetMapping("/orders")
    public String orderView(){

        return "/orderList/orders2";
    }

    @GetMapping("/productionStatus")
    public String productionStatus(){
        return "/production/productionStatus";
    }
}
