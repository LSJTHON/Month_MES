package com.CabbageAndGarlic.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderViewController {

    @GetMapping("/hello")
    public String hello(){

        return "/orderList/orders2";
    }


}
