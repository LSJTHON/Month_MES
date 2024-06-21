package com.CabbageAndGarlic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/supplier")
public class SupplierManageController {

    @GetMapping
    public String getSupplierManagementPage(Model model) {
        return "/Supplier/Supplier"; // 뷰 템플릿 이름
    }
}


