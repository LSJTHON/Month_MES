package com.CabbageAndGarlic.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "inout_bound") //입출고
public class InOutBound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "material_code")
    private Material materialCode; // 자재코드

    @ManyToOne
    @JoinColumn(name = "product_code")
    private Product productCode; // 제품코드

    @Column(name = "inout_bound_date", nullable = false)
    private LocalDateTime inoutBoundDate; // 입출고일

    @Column(name = "transaction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType; // 입출고 타입

    @Column(name = "quantity", nullable = false)
    private int quantity; // 수량

    @Column(name = "manager", nullable = false)
    private String manager; // 담당자


    public enum TransactionType {
        IN, OUT
    }
}
