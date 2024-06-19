package com.CabbageAndGarlic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name="production_plan_order_map")
public class ProductionPlanOrderMap {
    @Id
    @Column(name = "order_plan")
    private Long orderPlan;

    @ManyToOne
    @JoinColumn(name = "order_number", nullable = false)
    private Order orderNumber;  // 수주번호

    @ManyToOne
    @JoinColumn(name = "production_plan_number", nullable = false)
    private ProductionPlan productionPlanNumber;

}
