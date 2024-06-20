package com.CabbageAndGarlic.service;

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

}
