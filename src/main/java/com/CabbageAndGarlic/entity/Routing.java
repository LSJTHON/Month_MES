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
    private ProcessManagement routingNumber;  // 라우팅 번호

    @ManyToOne
    @JoinColumn(name = "process_name", nullable = false)
    private ProcessManagement routingProduct;  // 공정명

    @ManyToOne
    @JoinColumn(name = "cycle_hour", nullable = false)
    private ProcessManagement cycleHour;  // 작업시간

    @ManyToOne
    @JoinColumn(name = "product_name", nullable = false)
    private Product routingProductName;  // 품목명

    @Column(name = "all_cycle_time", nullable = false)
    private Integer allCycleTime;  // 전체공정 시간
}
