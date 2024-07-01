package com.CabbageAndGarlic.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "order_production_plan")
public class OrderProductionPlan {
    @Id
    @Column(name = "order_production_plan_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderProductionPlanNumber;  // 중간다리번호

    @ManyToOne
    @JoinColumn(name = "order_number", nullable = false)
    private Order orderNumber;  // 수주번호

    @ManyToOne
    @JoinColumn(name = "production_plan", nullable = false)
    private ProductionPlan productionPlan; //생산계획번호
}
