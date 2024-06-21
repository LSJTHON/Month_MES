package com.CabbageAndGarlic.entity;

import com.CabbageAndGarlic.constant.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "purchase_order") //발주
public class PurchaseOrder extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_number", nullable = false)
    private Long purchaseNumber;  // 발주번호

    @Column(name = "purchase_order_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status purchaseStatus;  // 발주상태

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private SupplierMaterial supplierMaterial;  // 원자재발주처코드 /자재, 발주처, 담당자 알 수 있음

    @ManyToOne
    @JoinColumn(name = "order_number", nullable = false)
    private Order order;  //수주번호

    @Column(name = "amount", nullable = false)
    private int amount;     //발주수량

    @Column(name = "receipt_date", nullable = false)
    private LocalDateTime receiptDate;  // 입고일 / regTime+leadTime

    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime purchaseDate; //발주일 / 당일
}
