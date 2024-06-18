package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.productListViewResponse;
import com.CabbageAndGarlic.service.productService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class productViewController {

    @GetMapping(value = "/test")
    public String product(Model model) {

        return "productView";
    }


//    private final productService ProductService;
//
//    @GetMapping("/product")
//    public String getProduct(Model model){
//        List<productListViewResponse> products = ProductService.findAll()
//                .stream()
//                .map(productListViewResponse::new)
//                .toList();
//
//        model.addAttribute("products",products);
//        return "productView";
//    }



}
