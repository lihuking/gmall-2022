package com.lihuking.gmall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lihuking.gmall.bean.BaseAttrInfo;
import com.lihuking.gmall.bean.BaseCatalog1;
import com.lihuking.gmall.bean.BaseCatalog2;
import com.lihuking.gmall.bean.BaseCatalog3;
import com.lihuking.gmall.service.ManageService;
import com.lihuking.gmall.service.mapper.*;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ManageServiceImpl implements ManageService {

    @Resource
    BaseAttrInfoMapper baseAttrInfoMapper;

    @Resource
    BaseAttrValueMapper baseAttrValueMapper;

    @Resource
    BaseCatalog1Mapper baseCatalog1Mapper;

    @Resource
    BaseCatalog2Mapper baseCatalog2Mapper;

    @Resource
    BaseCatalog3Mapper baseCatalog3Mapper;

    @Resource
    public List<BaseCatalog1> getCatalog1() {
        List<BaseCatalog1> baseCatalog1List = baseCatalog1Mapper.selectAll();
        return baseCatalog1List;
    }

    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        BaseCatalog2 baseCatalog2=new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);

        List<BaseCatalog2> baseCatalog2List = baseCatalog2Mapper.select(baseCatalog2);
        return baseCatalog2List;
    }

    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
        BaseCatalog3 baseCatalog3=new BaseCatalog3();
        baseCatalog3.setCatalog2Id(catalog2Id);

        List<BaseCatalog3> baseCatalog3List = baseCatalog3Mapper.select(baseCatalog3);
        return baseCatalog3List;
    }

    @Override
    public List<BaseAttrInfo> getAttrList(String catalog3_id) {
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setCatalog3Id(catalog3_id);

        List<BaseAttrInfo> baseAttrInfoList = baseAttrInfoMapper.select(baseAttrInfo);
        return baseAttrInfoList;

    }
}