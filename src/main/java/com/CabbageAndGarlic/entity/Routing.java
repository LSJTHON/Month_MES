package com.CabbageAndGarlic.entity;


import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "routing")
public class Routing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @ManyToOne
    @JoinColumn(name = "process_number")
    private ProcessManagement process_number;  // 라우팅 번호

    @ManyToOne
    @JoinColumn(name = "process_name", nullable = false)
    private ProcessManagement routingProduct;  // 공정명

    @ManyToOne
    @JoinColumn(name = "cycle_hour", nullable = false)
    private ProcessManagement cycleHour2;  // 작업시간

    @Column(name = "cycle_time", nullable = false)
    private Integer cycleTime;  // 전체공정 시간
}
