package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.entity.SupplierManage;
import com.CabbageAndGarlic.service.SupplierManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/supplier-manages")
public class SupplierManageController {

    @Autowired
    private SupplierManageService supplierManageService;

    //발주처페이지
    @GetMapping
    public String viewSupplierPage(Model model) {
        return "Supplier/Supplier";
    }

    //발주처목록가져옴
    @GetMapping("/all")
    @ResponseBody
    public List<SupplierManage> getAllSupplierManages() {
        return supplierManageService.getAllSupplierManages();
    }

    //특정 발주처코드를 기반으로 발주처 가져옴
    @GetMapping("/{supplierCode}")
    @ResponseBody
    public Optional<SupplierManage> getSupplierManageById(@PathVariable String supplierCode) {
        return supplierManageService.getSupplierManageById(supplierCode);
    }

    //발주처 생성
    @PostMapping
    @ResponseBody
    public SupplierManage createSupplierManage(@RequestBody SupplierManage supplierManage) {
        return supplierManageService.createSupplierManage(supplierManage);
    }

    //기존 발주처 업데이트
    @PutMapping("/{supplierCode}")
    @ResponseBody
    public SupplierManage updateSupplierManage(@PathVariable String supplierCode, @RequestBody SupplierManage supplierManageDetails) {
        return supplierManageService.updateSupplierManage(supplierCode, supplierManageDetails);
    }

    //특정 발주처코드를 기반으로 발주처 삭제
    @DeleteMapping("/{supplierCode}")
    @ResponseBody
    public void deleteSupplierManage(@PathVariable String supplierCode) {
        supplierManageService.deleteSupplierManage(supplierCode);
    }
}
