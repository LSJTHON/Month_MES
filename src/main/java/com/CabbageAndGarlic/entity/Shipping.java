package com.CabbageAndGarlic.entity;


import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shippingNumber;  // 출하번호

    @Column(name = "shipping_date", nullable = false)
    private LocalDateTime shippingDate;  // 출하일

    @Column(name = "shipping_company", nullable = false)
    private String shippingCompany;  // 운송업체

    @ManyToOne
    @JoinColumn(name = "order_number", nullable = false)
    private Order order;  // 수주번호

    @Column(name = "product_code", nullable = false)
    private Long productCode;  // 품목코드
}
