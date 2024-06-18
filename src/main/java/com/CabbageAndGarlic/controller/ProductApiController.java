package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.AddProductRequest;
import com.CabbageAndGarlic.dto.ProductListViewResponse;
import com.CabbageAndGarlic.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductApiController {
    private final ProductService productService;
        @GetMapping("/products")
    public Map<String,Object> getAllProducts() {

        Map<String,Object> getAllProducts = new HashMap<>();

            getAllProducts.put("data",productService.findAll());

        return getAllProducts;
    }

    @PostMapping("/createProduct")
    public String saveOrder(@RequestBody AddProductRequest addProductRequest) {
        System.out.println(addProductRequest.getProductName()+"dwofjwioefjioefjioefjioefjifjioefjiofjiofj");
        productService.saveProduct(addProductRequest);
        return "등록 성공";
    }

}
