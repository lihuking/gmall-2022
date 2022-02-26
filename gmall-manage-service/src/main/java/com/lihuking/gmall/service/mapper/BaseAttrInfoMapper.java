package com.lihuking.gmall.service.mapper;

import com.lihuking.gmall.bean.BaseAttrInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaseAttrInfoMapper extends Mapper<BaseAttrInfo> {
    List<BaseAttrInfo> getAttrList(String catalog3_id);
}