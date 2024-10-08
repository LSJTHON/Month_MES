package com.CabbageAndGarlic.entity;


import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "routing")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Routing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "process_number")
    private ProcessManagement routingNumber;  // 공정 번호

    @ManyToOne
    @JoinColumn(name = "product_name", nullable = false)
    private Product routingProductName;  // 품목명

    @Column(name = "all_cycle_time", nullable = false)
    private Integer allCycleTime;  // 전체공정 시간

    @Builder
    public Routing(ProcessManagement routingNumber, Product routingProductName, Integer allCycleTime) {
        this.routingNumber = routingNumber;
        this.routingProductName = routingProductName;
        this.allCycleTime = allCycleTime;
    }

}
