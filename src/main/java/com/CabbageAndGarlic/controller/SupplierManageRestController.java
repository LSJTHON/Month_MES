package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.SupplierManageDto;
import com.CabbageAndGarlic.entity.Material;
import com.CabbageAndGarlic.entity.Supplier;
import com.CabbageAndGarlic.service.MaterialService;
import com.CabbageAndGarlic.service.SupplierManageService;
import com.CabbageAndGarlic.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/supplier")
public class SupplierManageRestController {

    @Autowired
    private SupplierManageService supplierManageService;

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private MaterialService materialService;

    @GetMapping("/manage")
    public List<SupplierManageDto> getSupplierManage() {
        return supplierManageService.getSupplierManageList();
    }

    @PostMapping("/manage")
    public void addSupplierManage(@RequestBody SupplierManageDto supplierManageDto) {
        supplierManageService.addSupplierManage(supplierManageDto);
    }

    @GetMapping("/suppliers")
    public List<Supplier> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/materials")
    public List<Material> getAllMaterials() {
        return materialService.getAllMaterials();
    }
}
