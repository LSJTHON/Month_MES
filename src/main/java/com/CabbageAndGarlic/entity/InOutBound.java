package com.CabbageAndGarlic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "inout_bound") //입출고
public class InOutBound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "purchase_number", nullable = false)
    private PurchaseOrder purchaseNumber; //발주번호 / 발주번호 및 원자재 발주처 테이블의 정보 이용

    @ManyToOne
    @JoinColumn(name = "material_code", nullable = false)
    private  Material materialCode; //자재코드

    @Column(name = "inout_bound_date")
    private LocalDateTime inoutBoundDate; // 입고일 -> 무조건 3일뒤? 계산할 때 3일로 설정. 변경불가
                                            //출고일 : 생산지시 내려올 때

    @Column(name = "transaction_type")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType; //입고출고 타입

    @Column(name = "quantity")
    private int quantity;       //수량


    public enum TransactionType {
        IN, OUT
    }
}
