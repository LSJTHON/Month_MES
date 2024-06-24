package com.CabbageAndGarlic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ShippingViewController {
    @GetMapping("/Shipping")
    public String orderView(){

        return "order/Shipping";
    }
}
