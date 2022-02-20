package com.lihuking.gmall.service;

import com.lihuking.gmall.bean.BaseAttrInfo;
import com.lihuking.gmall.bean.BaseCatalog1;
import com.lihuking.gmall.bean.BaseCatalog2;
import com.lihuking.gmall.bean.BaseCatalog3;

import java.util.List;

public interface ManageService {

     List<BaseCatalog1> getCatalog1();

     List<BaseCatalog2> getCatalog2(String catalog1Id);

     List<BaseCatalog3> getCatalog3(String catalog2Id);

     List<BaseAttrInfo> getAttrList(String catalog3Id);


}