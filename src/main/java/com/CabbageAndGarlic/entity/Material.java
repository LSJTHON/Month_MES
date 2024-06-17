package com.CabbageAndGarlic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "material")
public class Material {

    //자재코드
    @Id
    @Column(name = "material_code")
    private String materialCode;

    //자재명
    @Column(name = "material_name")
    private String materialName;

}
