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
    private Long routingNumber;  // 라우팅 번호

    @Column(name = "routing_product", nullable = false)
    private String routingProduct;  // 공정명

    @Column(name = "cycle_time", nullable = false)
    private Integer cycleTime;  // 사이클 시간
}
