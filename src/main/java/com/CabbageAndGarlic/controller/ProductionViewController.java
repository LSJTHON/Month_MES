package com.CabbageAndGarlic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/production")
@RequiredArgsConstructor
public class ProductionViewController {

    @GetMapping
    public String production() {
        return "production/product";
    }

}
