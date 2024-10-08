package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.dto.AddProcessManageRequest;
import com.CabbageAndGarlic.dto.AddProductRequest;
import com.CabbageAndGarlic.entity.ProcessManagement;
import com.CabbageAndGarlic.entity.Product;
import com.CabbageAndGarlic.repository.ProcessManageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProcessManageService {
private final ProcessManageRepository processManageRepository;

    public List<ProcessManagement> findAll() {
        return processManageRepository.findAll();
    }

    public ProcessManagement saveProcess(AddProcessManageRequest request) {
        return processManageRepository.save(request.toEntity());
    }

    public void deleteProcess(String processNumber){
        processManageRepository.deleteById(processNumber);
    }

}
