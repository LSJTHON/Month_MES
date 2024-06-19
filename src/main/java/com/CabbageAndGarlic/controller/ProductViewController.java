package com.CabbageAndGarlic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class ProductViewController {

    @GetMapping(value = "/product")
    public String product(Model model) {

        return "product/ProductView";
    }


    @GetMapping(value = "/material")
    public String material(Model model) {

        return "product/MaterialView";
    }

}
