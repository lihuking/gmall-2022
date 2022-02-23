package com.lihuking.gmall.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
public class SpuSaleAttrValue implements Serializable {

    @Id
    @Column
    String id ;

    @Column
    String spuId;

    @Column
    String saleAttrId;

    @Column
    String saleAttrValueName;

    @Transient
    String isChecked;

 }