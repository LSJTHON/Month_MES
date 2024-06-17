package com.CabbageAndGarlic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "process_management")
public class ProcessManagement {
    @Id
    @Column(name = "process_number")
    private Long processNumber;  // 공정번호
    //공정번호는 알파벳을 섞을 것인가?

    @Column(name = "process_name", nullable = false)
    private String processName;  // 공정명

    @Column(name = "production_capacity", nullable = false)
    private Integer productionCapacity;  // 생산 가능량

    @Column(name = "production_setup_time", nullable = false)
    private Integer productionSetupTime;  // 생산 준비시간

    @Column(name = "cycle_hour", nullable = false)
    private Integer cycleHour;  // 사이클 시간
}
