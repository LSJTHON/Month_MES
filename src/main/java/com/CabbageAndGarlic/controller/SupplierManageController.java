package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.SupplierManageDto;
import com.CabbageAndGarlic.service.SupplierManageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierManageController {

    @GetMapping
    public String getSupplierManagementPage(Model model) {
        return "/Supplier/Supplier";
    }
}


