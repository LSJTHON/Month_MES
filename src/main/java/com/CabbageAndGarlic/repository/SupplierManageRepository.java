package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.Material;
import com.CabbageAndGarlic.entity.SupplierManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierManageRepository extends JpaRepository<SupplierManage, Long> {
}
