package com.CabbageAndGarlic.entity;

import com.CabbageAndGarlic.constant.Status;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Data
@Entity
@Getter
@Setter
@Table(name = "order_item") //수주 상품 테이블
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long orderItemId;  // 수주상품번호
    //수주 상품 번호는 알파벳을 섞을 것인가?

    @Column(name = "product_name", nullable = false)
    private String productName;  // 상품이름

    @Column(name = "amount", nullable = false)
    private Integer amount;  // 수량

    @ManyToOne
    @JoinColumn(name = "order_number", nullable = false)
    private Order orderNumber;  // 수주번호

    @Column(name = "start_date")
    private LocalDateTime startDate;  // 시작일

    @Column(name = "end_date")
    private LocalDateTime endDate;  // 종료일

    @Column(name = "status")
    private Status status; //상태
}
