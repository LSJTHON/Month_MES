package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.dto.SupplierManageDto;
import com.CabbageAndGarlic.entity.Material;
import com.CabbageAndGarlic.entity.Supplier;
import com.CabbageAndGarlic.entity.SupplierManage;
import com.CabbageAndGarlic.repository.MaterialRepository;
import com.CabbageAndGarlic.repository.SupplierManageRepository;
import com.CabbageAndGarlic.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierManageService {

    @Autowired
    private SupplierManageRepository supplierManageRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private MaterialRepository materialRepository;

    public List<SupplierManageDto> getSupplierManageList() {
        List<SupplierManage> supplierManages = supplierManageRepository.findAll();
        return supplierManages.stream().map(supplierManage -> SupplierManageDto.builder()
                .supplierCode(supplierManage.getSupplierCode().getSupplierCode())
                .supplierName(supplierManage.getSupplierCode().getSupplierName())
                .materialCode(supplierManage.getMaterialCode().getMaterialCode())
                .materialName(supplierManage.getMaterialCode().getMaterialName())
                .minAmount(supplierManage.getMinAmount())
                .maxAmount(supplierManage.getMaxAmount())
                .unitPrice(supplierManage.getUnitPrice())
                .build()).collect(Collectors.toList());
    }

    public void addSupplierManage(SupplierManageDto supplierManageDto) {
        Supplier supplier = supplierRepository.findById(supplierManageDto.getSupplierCode()).orElseThrow(() -> new RuntimeException("Supplier not found"));
        Material material = materialRepository.findById(supplierManageDto.getMaterialCode()).orElseThrow(() -> new RuntimeException("Material not found"));
        SupplierManage supplierManage = SupplierManage.builder()
                .supplierCode(supplier)
                .materialCode(material)
                .minAmount(supplierManageDto.getMinAmount())
                .maxAmount(supplierManageDto.getMaxAmount())
                .unitPrice(supplierManageDto.getUnitPrice())
                .build();
        supplierManageRepository.save(supplierManage);
    }
}
