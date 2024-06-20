package com.CabbageAndGarlic.dto;

import com.CabbageAndGarlic.entity.ProcessManagement;
import com.CabbageAndGarlic.entity.Product;
import lombok.Getter;

@Getter
public class ProcessManageListViewResponse {
    private final Long processNumber;
    private final String processName;
    private final int productionCapacity;
    private final int productionSetupTime;
    private final int cycleHour;

    public ProcessManageListViewResponse(ProcessManagement processManagement) {
        this.processNumber = processManagement.getProcessNumber();
        this.processName = processManagement.getProcessName();
        this.productionCapacity = processManagement.getProductionCapacity();
        this.productionSetupTime = processManagement.getProductionSetupTime();
        this.cycleHour = processManagement.getCycleHour();

    }
}
