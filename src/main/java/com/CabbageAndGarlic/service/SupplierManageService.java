package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.entity.SupplierManage;
import com.CabbageAndGarlic.repository.SupplierManageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierManageService {

    @Autowired
    private SupplierManageRepository supplierManageRepository;

    //발주처 목록 가져오기
    public List<SupplierManage> getAllSupplierManages() {
        return supplierManageRepository.findAll();
    }

    //특정 발주처 코드를 기반으로 발주처를 가져옴
    public Optional<SupplierManage> getSupplierManageById(String supplierCode) {
        return supplierManageRepository.findById(supplierCode);
    }

    //새로운 발주처 생성
    public SupplierManage createSupplierManage(SupplierManage supplierManage) {
        return supplierManageRepository.save(supplierManage);
    }

    //기존 발주처 정보 업데이트
    public SupplierManage updateSupplierManage(String supplierCode, SupplierManage supplierManageDetails) {
        SupplierManage supplierManage = supplierManageRepository.findById(supplierCode)
                .orElseThrow(() -> new RuntimeException("SupplierManage not found"));
        supplierManage.setSupplierName(supplierManageDetails.getSupplierName());
        supplierManage.setManager(supplierManageDetails.getManager());
        return supplierManageRepository.save(supplierManage);
    }

    //특정 발주처 코드를 기반으로 발주처 삭제
    public void deleteSupplierManage(String supplierCode) {
        SupplierManage supplierManage = supplierManageRepository.findById(supplierCode)
                .orElseThrow(() -> new RuntimeException("SupplierManage not found"));
        supplierManageRepository.delete(supplierManage);
    }
}
