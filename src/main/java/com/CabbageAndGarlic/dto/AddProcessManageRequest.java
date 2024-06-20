package com.CabbageAndGarlic.dto;

import com.CabbageAndGarlic.entity.ProcessManagement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddProcessManageRequest {
    private String processNumber;
    private String processName;
    private Integer productionCapacity;
    private Integer productionSetupTime;
    private Integer cycleHour;

    public ProcessManagement toEntity() {
        return ProcessManagement.builder()
                .processNumber(processNumber)
                .processName(processName)
                .productionCapacity(productionCapacity)
                .productionSetupTime(productionSetupTime)
                .cycleHour(cycleHour)
                .build();
    }
}
