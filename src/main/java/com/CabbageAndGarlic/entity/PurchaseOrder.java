package com.CabbageAndGarlic.entity;

import com.CabbageAndGarlic.constant.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @JoinColumn(name = "id")
    private SupplierManage supplierManage;  // 원자재발주처코드 /자재, 발주처, 담당자 알 수 있음

//    @ManyToOne
//    @JoinColumn(name = "order_number")
//    private Order order;  //수주번호

    @Column(name = "amount", nullable = false)
    private int amount;     //발주수량

    @Column(name = "receipt_date", nullable = false)
    private LocalDateTime receiptDate;  // 입고일 -> 발주일 + 2일

    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime purchaseDate; //발주일 / 발주당일

    @Column(name = "manager", nullable = false)
    private String manager; // 담당자

    public enum PuchaseOrderStateType {
        InProgress,
        Completed
    }


}
