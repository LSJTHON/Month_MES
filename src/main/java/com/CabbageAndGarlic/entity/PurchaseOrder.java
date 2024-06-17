package com.CabbageAndGarlic.entity;

import com.CabbageAndGarlic.constant.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "purchase_order")
public class PurchaseOrder extends BaseTimeEntity {
    @Id
    @Column(name = "purchase_number")
    private Long purchaseNumber;  // 발주번호

    @Column(name = "purchase_order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status purchaseStatus;  // 발주상태

    @Column(name = "supplier", nullable = false)
    private String supplier;  // 발주처

    @Column(name = "manager")
    private String manager;  // 담당자

    @Column(name = "receipt_date")
    private LocalDateTime receiptDate;  // 입고일
}
