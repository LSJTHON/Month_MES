package com.CabbageAndGarlic.entity;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "purchased_item")//발주랑 자재랑 연결
public class PurchasedItem{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "purchase_number", nullable = false)
    private PurchaseOrder purchaseNumber;  // 발주번호

    @ManyToOne
    @JoinColumn(name = "material_code", nullable = false)
    private Material materialCode;  // 자재코드


    @Column(name = "amount", nullable = false)
    private Integer amount;  // 수량

}
