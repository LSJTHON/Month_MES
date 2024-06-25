package com.CabbageAndGarlic.repository;

import com.CabbageAndGarlic.entity.ProcessManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessManageRepository extends JpaRepository<ProcessManagement,String> {
    ProcessManagement findByProcessName(String processName);

}
