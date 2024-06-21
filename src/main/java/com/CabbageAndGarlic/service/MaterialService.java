package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.dto.AddMaterialRequest;
import com.CabbageAndGarlic.dto.AddProductRequest;
import com.CabbageAndGarlic.entity.Material;
import com.CabbageAndGarlic.entity.Product;
import com.CabbageAndGarlic.repository.MaterialRepository;
import com.CabbageAndGarlic.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;

    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    public Material saveMaterial(AddMaterialRequest request) {
        return materialRepository.save(request.toEntity());
    }

    public void deleteMaterial(String materialCode){
        materialRepository.deleteById(materialCode);
    }

    //-----eunji--------------------------------------------------------------------------------

    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

}

