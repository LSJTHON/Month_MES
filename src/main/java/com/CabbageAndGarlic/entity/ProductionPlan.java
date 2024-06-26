package com.CabbageAndGarlic.entity;


import com.CabbageAndGarlic.constant.Status;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "production_plan")
public class ProductionPlan {
    @Id
    @Column(name = "production_plan_number")
    private Long productionPlanNumber;  // 생산계획번호

    @ManyToOne
    @JoinColumn(name = "order_number", nullable = false)
    private Order orderNumber;  // 수주번호

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;  // 시작일

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;  // 종료일

    @Column(name = "production_plan_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status productionPlanStatus;  // 상태
}
