package com.CabbageAndGarlic.dto;

import com.CabbageAndGarlic.entity.Material;
import com.CabbageAndGarlic.entity.SupplierManage;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SupplierDto {

    private SupplierManage supplierCode; //발주처코드

    private SupplierManage supplierName; //발주처명

    private Material materialCode; //자재코드

    private Material materialName; //자재명

    private int minAmount; //최소주문수량

    private int maxAmount; //최대주문수량

    private int unitPrice; //단가

//    public Supplier toEntity(){
//        return Supplier.builder()
//                .supplierCode(supplierCode)
//                .supplierCode(supplierName)
//                .materialCode(materialCode)
//                .materialCode(materialName)
//                .minAmount(minAmount)
//                .maxAmount(maxAmount)
//                .unitPrice(unitPrice)
//                .build(); // Supplier 객체 생성 완료
//    }


    public SupplierDto() {
        this.supplierCode = getSupplierCode();
        this.supplierName = getSupplierName();
        this.materialCode = getMaterialCode();
        this.materialName = getMaterialName();
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
        this.unitPrice = unitPrice;
    }
}
