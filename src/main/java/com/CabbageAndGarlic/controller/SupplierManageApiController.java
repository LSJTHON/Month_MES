package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.SupplierManageDto;
import com.CabbageAndGarlic.entity.Material;
import com.CabbageAndGarlic.entity.Supplier;
import com.CabbageAndGarlic.service.MaterialService;
import com.CabbageAndGarlic.service.SupplierManageService;
import com.CabbageAndGarlic.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
@RequiredArgsConstructor
public class SupplierManageApiController {

    private final SupplierManageService supplierManageService;
    private final SupplierService supplierService;
    private final MaterialService materialService;

    @GetMapping("/manage")
    public List<SupplierManageDto> getSupplierManage() {
        return supplierManageService.getSupplierManageList(); // 공급자 관리 목록을 조회하여 반환
    }

    @PostMapping("/manage")
    public void addSupplierManage(@RequestBody SupplierManageDto supplierManageDto) {
        supplierManageService.addSupplierManage(supplierManageDto); // 공급자 관리 정보를 추가
    }

    @GetMapping("/suppliers")
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers(); // 모든 공급자 정보를 조회하여 반환
    }

    @GetMapping("/materials")
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials(); // 모든 재료 정보를 조회하여 반환
    }
}
