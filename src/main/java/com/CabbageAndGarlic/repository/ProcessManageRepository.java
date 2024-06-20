package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.ProcessManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessManageRepository extends JpaRepository<ProcessManagement,String> {
}
