package com.CabbageAndGarlic.service;

import com.CabbageAndGarlic.dto.AddMaterialRequest;
import com.CabbageAndGarlic.entity.Material;
import com.CabbageAndGarlic.repository.MaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;

    public List<Material> findAll() {
        // 모든 자재 정보 조회
        return materialRepository.findAll();
    }

    public Material saveMaterial(AddMaterialRequest request) {
        // 자재 정보 저장
        // DTO에서 엔티티 변환은 DTO 클래스에서 toEntity 메서드를 통해 이루어질 것으로 예상
        return materialRepository.save(request.toEntity());
    }

    public void deleteMaterial(String materialCode){
        // 특정 자재 코드에 해당하는 자재 정보 삭제
        materialRepository.deleteById(materialCode);
    }
//-----------------------------------------------------------------------------------------
    // 아래 메서드는 findAll() 메서드와 기능이 중복됩니다.
    public List<Material> getAllMaterials() {
        // 모든 자재 정보 조회
        return materialRepository.findAll();
    }

}
