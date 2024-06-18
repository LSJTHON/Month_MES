package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.service.productService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class productApiController {
    private final productService ProductService;
        @GetMapping("/api/products")
    public Map<String,Object> getAllProducts() {

        Map<String,Object> getAllProducts = new HashMap<>();

            getAllProducts.put("data",ProductService.findAll());

        return getAllProducts;
    }

//    @PostMapping("/createProduct")
//    public String saveOrder(@RequestBody OrderDto orderDto) {
//        productService.saveOrder(orderDto);
//        return "등록 성공";
//    }

}
