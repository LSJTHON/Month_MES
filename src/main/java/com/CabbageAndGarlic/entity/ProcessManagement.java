package com.CabbageAndGarlic.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "process_management")
public class ProcessManagement {
    @Id
    @Column(name = "process_number")
    private String processNumber;  // 공정번호
    //공정번호는 알파벳을 섞을 것인가?

    @Column(name = "process_name", nullable = false)
    private String processName;  // 공정명

    @Column(name = "production_capacity", nullable = false)
    private Integer productionCapacity;  // 생산 가능량

    @Column(name = "production_setup_time", nullable = false)
    private Integer productionSetupTime;  // 생산 가능량

    @Column(name = "cycle_hour", nullable = false)
    private Integer cycleHour;  // 작업 시간

    @Builder
    public ProcessManagement(String processNumber, String processName, int productionCapacity, int productionSetupTime, int cycleHour) {
        this.processNumber = processNumber;
        this.processName = processName;
        this.productionCapacity = productionCapacity;
        this.productionSetupTime = productionSetupTime;
        this.cycleHour = cycleHour;
    }
}

