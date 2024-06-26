package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.SupplierManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierManageRepository extends JpaRepository<SupplierManage, Long> {
}
