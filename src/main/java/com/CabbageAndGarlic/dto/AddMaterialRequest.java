package com.CabbageAndGarlic.dto;

import com.CabbageAndGarlic.entity.Material;
import com.CabbageAndGarlic.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddMaterialRequest {
    private String materialCode;
    private String materialName;

    public Material toEntity() {
        return Material.builder()
                .materialCode(materialCode)
                .materialName(materialName)
                .build();
    }

}
