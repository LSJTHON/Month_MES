package com.CabbageAndGarlic.entity;


import com.CabbageAndGarlic.constant.Status;


import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "WorkOrder")
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_order_number")
    private Long workOrderNumber;  // 작업지시번호

    @Column(name = "process", nullable = false)
    private String process;  // 공정

    @Column(name = "start_time_of_operation")
    private LocalDateTime startTimeOfOperation;  // 공정시작시간

    @Column(name = "productType", nullable = false)
    private String productType;  //작업품목

    @Column(name = "work_amount", nullable = false)
    private Integer workAmount;  // 작업량

    @Column(name = "worker", nullable = false)
    private String worker;  // 작업자

    @Column(name = "work_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status workStatus;  // 상태

    @Column(name = "orderDate", nullable = false)
    private LocalDate orderDate; //지시날짜
}
