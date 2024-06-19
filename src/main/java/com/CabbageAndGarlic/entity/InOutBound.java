package com.CabbageAndGarlic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "inoutBound") //입출고
public class InOutBound {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @ManyToOne
    @JoinColumn(name = "purchase_number", nullable = false)
    private PurchaseOrder purchaseNumber; //발주처명

    @ManyToOne
    @JoinColumn(name = "material_name", nullable = false)
    private  Material materialName; //자재명

    @Column(name = "inbound_date", nullable = false)
    private LocalDateTime inboundDate; //입고일 -> 무조건 3일뒤?

    @Column(name = "outbound_date")
    private LocalDateTime outboundDate; //출고일

    @Column(name = "location")
    private String location; //위치 -> 어떻게 처리하지


}
