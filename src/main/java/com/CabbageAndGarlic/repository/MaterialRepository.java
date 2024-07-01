package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material ,String> {
    Optional<Material> findByMaterialName(String materialName);
}

