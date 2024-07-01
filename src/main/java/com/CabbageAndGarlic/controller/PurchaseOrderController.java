package com.CabbageAndGarlic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchase")
public class PurchaseOrderController {

    @GetMapping
    public String getPurchaseOrderPage(Model model) {
        return "/Purchase/Purchase";
    }
}
