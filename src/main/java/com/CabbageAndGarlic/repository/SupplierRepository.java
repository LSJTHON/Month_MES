package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, String> {
}
