package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.AddMaterialRequest;
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
    private final ProductService productService; // 제품 서비스
    private final MaterialService materialService; // 자재 서비스

    @GetMapping("/products") // 제품 목록 조회
    public Map<String,Object> getAllProducts() {
        Map<String,Object> getAllProducts = new HashMap<>();
        getAllProducts.put("data", productService.findAll()); // 모든 제품을 조회하여 'data' 키에 저장
        return getAllProducts;
    }

    @PostMapping("/createProduct") // 제품 등록
    public String saveProduct(@RequestBody AddProductRequest addProductRequest) {
        System.out.println(addProductRequest.getProductName() + "dwofjwioefjioefjioefjioefjifjioefjiofjiofj"); // 콘솔 로그
        productService.saveProduct(addProductRequest); // 제품 정보 저장
        return "등록 성공";
    }

    @DeleteMapping("/products/{productCode}") // 제품 삭제
    public ResponseEntity<Void> deleteProduct(@PathVariable String productCode) {
        productService.delete(productCode); // 제품 코드에 해당하는 제품 삭제
        return ResponseEntity.ok().build(); // 200 OK 응답
    }

    @GetMapping("/materials") // 자재 목록 조회
    public Map<String,Object> getAllMaterial() {
        Map<String,Object> getAllMaterial = new HashMap<>();
        getAllMaterial.put("data", materialService.findAll()); // 모든 자재를 조회하여 'data' 키에 저장
        return getAllMaterial;
    }

    @PostMapping("/createMaterial") // 자재 등록
    public String saveMaterial(@RequestBody AddMaterialRequest addMaterialRequest) {
        System.out.println(addMaterialRequest.getMaterialCode() + "dwofjwioefjioefjioefjioefjifjioefjiofjiofj"); // 콘솔 로그
        materialService.saveMaterial(addMaterialRequest); // 자재 정보 저장
        return "등록 성공"; // 등록 성공 메시지 반환
    }

    @DeleteMapping("/materials/{materialCode}") // 자재 삭제
    public ResponseEntity<Void> deleteMaterial(@PathVariable String materialCode) {
        materialService.deleteMaterial(materialCode); // 자재 코드에 해당하는 자재 삭제
        return ResponseEntity.ok().build(); // 200 OK 응답
    }
}
