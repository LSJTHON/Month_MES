package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.dto.SupplierManageDto;
import com.CabbageAndGarlic.entity.Material;
import com.CabbageAndGarlic.entity.Supplier;
import com.CabbageAndGarlic.entity.SupplierManage;
import com.CabbageAndGarlic.repository.MaterialRepository;
import com.CabbageAndGarlic.repository.SupplierManageRepository;
import com.CabbageAndGarlic.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierManageService {

    private final SupplierManageRepository supplierManageRepository; // 공급자 관리 데이터 접근 리포지토리
    private final SupplierRepository supplierRepository; // 공급자 데이터 접근 리포지토리
    private final MaterialRepository materialRepository; // 자재 데이터 접근 리포지토리

    public List<SupplierManageDto> getSupplierManageList() {
        // 공급자 관리 목록 조회
        List<SupplierManage> supplierManages = supplierManageRepository.findAll();
        return supplierManages.stream().map(supplierManage -> SupplierManageDto.builder()
                .supplierCode(supplierManage.getSupplierCode().getSupplierCode()) // 공급자 코드
                .supplierName(supplierManage.getSupplierCode().getSupplierName()) // 공급자 이름
                .materialCode(supplierManage.getMaterialCode().getMaterialCode()) // 자재 코드
                .materialName(supplierManage.getMaterialCode().getMaterialName()) // 자재 이름
                .minAmount(supplierManage.getMinAmount()) // 최소 구매 수량
                .maxAmount(supplierManage.getMaxAmount()) // 최대 구매 수량
                .unitPrice(supplierManage.getUnitPrice()) // 단가
                .build()).collect(Collectors.toList()); // 리스트로 수집
    }

    public void addSupplierManage(SupplierManageDto supplierManageDto) {
        // 공급자 관리 정보 추가
        Supplier supplier = supplierRepository.findById(supplierManageDto.getSupplierCode()).orElseThrow(() -> new RuntimeException("Supplier not found")); // 공급자 조회
        Material material = materialRepository.findById(supplierManageDto.getMaterialCode()).orElseThrow(() -> new RuntimeException("Material not found")); // 자재 조회
        SupplierManage supplierManage = SupplierManage.builder()
                .supplierCode(supplier) // 공급자 설정
                .materialCode(material) // 자재 설정
                .minAmount(supplierManageDto.getMinAmount()) // 최소 수량 설정
                .maxAmount(supplierManageDto.getMaxAmount()) // 최대 수량 설정
                .unitPrice(supplierManageDto.getUnitPrice()) // 단가 설정
                .build();
        supplierManageRepository.save(supplierManage); // 저장
    }
}
