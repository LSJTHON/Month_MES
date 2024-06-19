package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.AddProductRequest;
import com.CabbageAndGarlic.dto.ProductListViewResponse;
import com.CabbageAndGarlic.service.MaterialService;
import com.CabbageAndGarlic.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductApiController {
    private final ProductService productService;
    private final MaterialService materialService;
        @GetMapping("/products") // 완제품조회
    public Map<String,Object> getAllProducts() {

        Map<String,Object> getAllProducts = new HashMap<>();

            getAllProducts.put("data",productService.findAll());

        return getAllProducts;
    }

    @GetMapping("/materials") // 자재 조회
    public Map<String,Object> getAllMaterial() {

        Map<String,Object> getAllMaterial = new HashMap<>();

        getAllMaterial.put("data",materialService.findAll());

        return getAllMaterial;
    }

    @PostMapping("/createProduct")
    public String saveOrder(@RequestBody AddProductRequest addProductRequest) {
        System.out.println(addProductRequest.getProductName()+"dwofjwioefjioefjioefjioefjifjioefjiofjiofj");
        productService.saveProduct(addProductRequest);
        return "등록 성공";
    }

    @DeleteMapping("/products/{productCode}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productCode) {
        productService.delete(productCode);

        return ResponseEntity.ok()
                .build();
    }


}
