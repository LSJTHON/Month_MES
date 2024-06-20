package com.CabbageAndGarlic.dto;

import com.CabbageAndGarlic.entity.Material;
import lombok.Getter;

@Getter
public class MaterialListViewResponse {
    private final String materialCode;
    private final String materialName;
    private final int materialAmount;

    public MaterialListViewResponse(Material material) {
        this.materialCode = material.getMaterialCode();
        this.materialName = material.getMaterialName();
        this.materialAmount = material.getMaterialAmount();
    }
}
