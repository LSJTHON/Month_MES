package com.CabbageAndGarlic.entity;

import com.CabbageAndGarlic.constant.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "order_table") //수주 테이블
public class Order extends BaseTimeEntity {
    @Id
    @Column(name = "order_number")
    private Long orderNumber;  // 수주번호

    @Column(name = "client", nullable = false)
    private String client;  // 수주처

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private Status status;  // 수주상태

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;  // 납품일

    @Column(name = "phone_number")
    private String phoneNumber;  // 연락처

//    @Column(name = "cancel_reason")
//    private String cancelReason;  // 취소사유

    @Column(name = "manager")
    private String manager;  // 담당자

//    @Column(name = "defect_percentage")
//    private String defectPercentage;  // 불량률

//    @Column(name = "capa")
//    private String capa;  // CAPA

}
