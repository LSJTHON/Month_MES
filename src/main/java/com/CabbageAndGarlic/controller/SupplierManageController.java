package com.CabbageAndGarlic.controller;

import com.CabbageAndGarlic.dto.SupplierDto;
import com.CabbageAndGarlic.entity.SupplierManage;
import com.CabbageAndGarlic.service.SupplierManageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/supplier")
@RequiredArgsConstructor
public class SupplierManageController {

    private final SupplierManageService supplierManageService;

    //발주처 정보 조회
    @GetMapping
    public ResponseEntity<List<SupplierDto>> getAllSuppliers() {
        List<SupplierManage> suppliers = supplierManageService.getAllSuppliers();
        List<SupplierDto> supplierDtos = suppliers.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(supplierDtos);
    }

    //특정 발주처코드를 기반으로 발주처 정보 조회
    @GetMapping("/{supplierCode}")
    public ResponseEntity<SupplierDto> getSupplierByCode(@PathVariable String supplierCode) {
        SupplierManage supplier = supplierManageService.getSupplierByCode(supplierCode);
        return ResponseEntity.ok(convertToDto(supplier));
    }

    //발주처 추가
    @PostMapping
    public ResponseEntity<SupplierDto> createSupplier(@RequestBody SupplierDto supplierDto) {
        SupplierManage supplier = supplierManageService.createSupplier(convertToEntity(supplierDto));
        return ResponseEntity.ok(convertToDto(supplier));
    }

    //특정 발주처코드를 기반으로 발주처 업데이트
    @PutMapping("/{supplierCode}")
    public ResponseEntity<SupplierDto> updateSupplier(@PathVariable String supplierCode, @RequestBody SupplierDto supplierDto) {
        SupplierManage updatedSupplier = supplierManageService.updateSupplier(supplierCode, convertToEntity(supplierDto));
        return ResponseEntity.ok(convertToDto(updatedSupplier));
    }

    //특정 발주처코드 삭제
    @DeleteMapping("/{supplierCode}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable String supplierCode) {
        supplierManageService.deleteSupplier(supplierCode);
        return ResponseEntity.noContent().build();
    }

    //SupplierManage 엔터티를 SupplierManageDto로 변환
    private SupplierDto convertToDto(SupplierManage supplierManage) {
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setSupplierCode(supplierDto.getSupplierCode());
        supplierDto.setSupplierName(supplierDto.getSupplierName());
        return supplierDto;
    }

    //SupplierManageDto를 SupplierManage 엔터티로 변환
    private SupplierManage convertToEntity(SupplierDto supplierDto) {
        SupplierManage supplier = new SupplierManage();
        supplier.setSupplierCode(supplierDto.getSupplierCode());
        supplier.setSupplierName(supplierDto.getSupplierName());
        supplier.setManager(supplierDto.getManager());
        return supplier;
    }
}
