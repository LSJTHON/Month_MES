package com.CabbageAndGarlic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "material") //자재
public class Material {

    //자재코드
    @Id
    @Column(name = "material_code")
    private String materialCode;

    //자재명
    @Column(name = "material_name")
    private String materialName;

    //자재양
    @Column(name = "material_amount")
    private int materialAmount;

    @Builder
    public Material(String materialCode, String materialName,int materialAmount) {
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.materialAmount = materialAmount;
    }
}
