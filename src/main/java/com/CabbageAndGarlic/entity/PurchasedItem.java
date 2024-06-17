package com.CabbageAndGarlic.entity;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "purchased_item")
public class PurchasedItem {
    @Id
    @Column(name = "purchased_item_id")
    private Long purchasedItemId;  // 발주상품번호

    @Column(name = "material_name", nullable = false)
    private String materialName;  // 자재명

    @Column(name = "amount", nullable = false)
    private Integer amount;  // 수량

    @Column(name = "supplier_code")
    private Long supplierCode;  // 발주처코드

    @ManyToOne
    @JoinColumn(name = "purchase_number", nullable = false)
    private PurchaseOrder purchaseNumber;  // 발주번호   1
}
