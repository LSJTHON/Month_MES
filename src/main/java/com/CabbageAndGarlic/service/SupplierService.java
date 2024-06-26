package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.entity.Supplier;
import com.CabbageAndGarlic.repository.SupplierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public List<Supplier> getAllSuppliers() {
        // 모든 공급자 정보를 조회
        return supplierRepository.findAll();
    }

}
