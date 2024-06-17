package com.CabbageAndGarlic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class productViewController {

    @GetMapping(value = "/test")
    public String product(Model model) {

        return "productView";
    }

}
