package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface MaterialRepository extends JpaRepository<Material ,String> {
    Material findByMaterialName(String materialName);
}

