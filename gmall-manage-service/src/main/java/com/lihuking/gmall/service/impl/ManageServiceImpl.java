package com.lihuking.gmall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lihuking.gmall.bean.*;
import com.lihuking.gmall.service.ManageService;
import com.lihuking.gmall.service.mapper.*;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

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
    SpuInfoMapper spuInfoMapper;

    @Resource
    BaseSaleAttrMapper baseSaleAttrMapper;
    
    @Resource
    SpuSaleAttrMapper spuSaleAttrMapper;

   @Resource
   SpuSaleAttrValueMapper  spuSaleAttrValueMapper;

   @Resource
   SpuImageMapper spuImageMapper;

   @Resource
   SkuInfoMapper skuInfoMapper;

   @Resource
   SkuImageMapper skuImageMapper;

   @Resource
   SkuAttrValueMapper skuAttrValueMapper;

   @Resource
   SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    /**
     * 获取所有的一级分类
     * @return
     */
    @Override
    public List<BaseCatalog1> getCatalog1() {
        List<BaseCatalog1> baseCatalog1List = baseCatalog1Mapper.selectAll();
        return baseCatalog1List;
    }

    /**
     * 获取所有的二级分类
     * @param catalog1Id
     * @return
     */
    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        BaseCatalog2 baseCatalog2=new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);
        List<BaseCatalog2> baseCatalog2List = baseCatalog2Mapper.select(baseCatalog2);
        return baseCatalog2List;
    }

    /**
     * 获取所有的三级分类
     * @param catalog2Id
     * @return
     */
    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
        BaseCatalog3 baseCatalog3=new BaseCatalog3();
        baseCatalog3.setCatalog2Id(catalog2Id);
        List<BaseCatalog3> baseCatalog3List = baseCatalog3Mapper.select(baseCatalog3);
        return baseCatalog3List;
    }

    /**
     * 获取三级分类下的所有属性列表
     * @param catalog3_id
     * @return
     */
    @Override
    public List<BaseAttrInfo> getAttrList(String catalog3_id) {

        List<BaseAttrInfo> baseAttrInfoList = baseAttrInfoMapper.getAttrList(catalog3_id);
        return baseAttrInfoList;
    }

    /**
     * 添加或者修改属性及属性值。
     * @param baseAttrInfo  基础属性。
     */
    @Transactional
    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        if (!StringUtil.isEmpty(baseAttrInfo.getId())){
               baseAttrInfoMapper.updateByPrimaryKeySelective(baseAttrInfo);
        }
        else {
           baseAttrInfoMapper.insertSelective(baseAttrInfo);

        }
           //获取属性值。
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        if (attrValueList!=null&&attrValueList.size()>0) {
            for (BaseAttrValue baseAttrValue : attrValueList) {
                baseAttrValue.setId(null);
                baseAttrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insertSelective(baseAttrValue);
            }
        }
    }

    /**
     * 获取具体某个商品属性的属性值。
     * @param attrId 商品属性。
     * @return
     */
    @Override
    public List<BaseAttrValue> getAttrValueList(String attrId) {
        Example example=new Example(BaseAttrValue.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("attrId", attrId);
        List<BaseAttrValue> attrValueList = baseAttrValueMapper.selectByExample(example);
        return attrValueList;
    }

    /**
     * 获取指定分类下的所有商品信息。
     * @param spuInfo
     * @return
     */
    @Override
    public List<SpuInfo> getSpuInfoList(SpuInfo spuInfo) {
        return spuInfoMapper.select(spuInfo);
    }

    /**
     * 获取销售属性列表。
     * @return
     */
    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        return baseSaleAttrMapper.selectAll();
    }

    /**
     * 保存商品信息。
     * @param spuInfo
     */
    @Transactional
    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        //存放商品基本信息
         spuInfoMapper.insertSelective(spuInfo);
         //存放商品销售属性及商品销售属性值
        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
            spuSaleAttr.setSpuId(spuInfo.getId());
            //存放商品销售属性
            spuSaleAttrMapper.insertSelective(spuSaleAttr);
            List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
            for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                spuSaleAttrValue.setSpuId(spuInfo.getId());
            //存放商品销售属性值。
            spuSaleAttrValueMapper.insertSelective(spuSaleAttrValue);
            }
        }
        //存放商品图片
        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        for (SpuImage spuImage : spuImageList) {
            spuImage.setSpuId(spuInfo.getId());
            spuImageMapper.insertSelective(spuImage);
        }
    }

    /**
     * 获取某类商品的所有图片
     * @param spuId
     * @return
     */
    @Override
    public List<SpuImage> getSpuImageList(String spuId) {
        SpuImage spuImage=new SpuImage();
        spuImage.setSpuId(spuId);
        List<SpuImage> spuImageList = spuImageMapper.select(spuImage);
        return spuImageList;
    }

    /**
     * 根据商品id,查询出商品的所有销售属性及销售属性值。
     * @param spuId
     * @return
     */
     @Override
     public List<SpuSaleAttr> getSpuSaleAttrList(String spuId) {
        List<SpuSaleAttr> spuSaleAttrList=  spuSaleAttrMapper.getSpuSaleAttrList(spuId);
        return spuSaleAttrList;
     }

    /**
     * 添加或者修改商品库存信息。
     * @param skuInfo
     */
    @Override
    public void saveSkuInfo(SkuInfo skuInfo) {
        //1、添加商品库存信息。
        skuInfoMapper.insertSelective(skuInfo);
        //2、添加商品库存图片
        List<SkuImage> skuImageList = skuInfo.getSkuImageList();
        if (skuImageList!=null&&skuImageList.size()>0) {
            for (SkuImage skuImage : skuImageList) {
                skuImage.setSkuId(skuInfo.getId());
                skuImageMapper.insertSelective(skuImage);
            }
        }
        //3、添加商品库存平台属性值。
        List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
        if (skuAttrValueList!=null&&skuAttrValueList.size()>0) {
            for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                skuAttrValue.setSkuId(skuInfo.getId());
                skuAttrValueMapper.insertSelective(skuAttrValue);
            }
        }
        //4、添加商品库存销售属性值。
        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        if (skuSaleAttrValueList!=null&&skuSaleAttrValueList.size()>0) {
            for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
                skuSaleAttrValue.setSkuId(skuInfo.getId());
                skuSaleAttrValueMapper.insertSelective(skuSaleAttrValue);
            }
        }
    }
}