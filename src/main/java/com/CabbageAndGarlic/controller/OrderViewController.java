package com.CabbageAndGarlic.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderViewController {

    @GetMapping("/orders")
    public String orderView(){
        return "order/orders2";
    }

    @GetMapping("/")
    public String productionStatus(){
        return "production/productionStatus";
    }
}
