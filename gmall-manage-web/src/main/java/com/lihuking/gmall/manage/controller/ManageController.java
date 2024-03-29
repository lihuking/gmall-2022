package com.lihuking.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lihuking.gmall.bean.*;
import com.lihuking.gmall.service.ManageService;
import org.csource.common.MyException;
import org.csource.fastdfs.FastDfsUploadTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@Controller
public class ManageController {

    @Reference
    private ManageService manageService;

    @RequestMapping("index" )
    public String manageIndex(){
        return "index";
    }

    /**
     * 获取一级分类
     * @return
     */
    @RequestMapping("getCatalog1")
    @ResponseBody
    public List<BaseCatalog1> getCatalog1(){

        return manageService.getCatalog1();
    }

    /**
     * 获取二级分类
     * @param catalog1Id
     * @return
     */
    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<BaseCatalog2> getCatalog2(String catalog1Id){
        return manageService.getCatalog2(catalog1Id);
    }

    /**
     * 获取三级分类
     * @param catalog2Id
     * @return
     */
    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<BaseCatalog3> getCatalog3(String catalog2Id){
        return manageService.getCatalog3(catalog2Id);
    }

    /**
     * 根据三级分类id获取属性信息列表
     * @param catalog3Id
     * @return
     */
    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<BaseAttrInfo> attrInfoList(String catalog3Id){
        return manageService.getAttrList(catalog3Id);
    }

    /**
     * 添加或者修改属性及属性值。
     * @param baseAttrInfo
     */
    //http://localhost:8082/saveAttrInfo
    @RequestMapping(value = "saveAttrInfo",method = RequestMethod.POST)
    @ResponseBody
    public void saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo){
        manageService.saveAttrInfo(baseAttrInfo);
    }

    /**
     * 根据属性id获取属性值。
     * @param attrId
     * @return
     */
    //http://localhost:8082/getAttrValueList?attrId=99
    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<BaseAttrValue> getAttrValueList(String attrId){
       List<BaseAttrValue> attrValueList= manageService.getAttrValueList(attrId);
       return attrValueList;
    }

    /**
     * 获取三级分类下的所有商品
     * @param catalog3Id
     * @return
     */
    @RequestMapping("spuList")
    @ResponseBody
    public List<SpuInfo> spuList(String catalog3Id){
        SpuInfo spuInfo = new SpuInfo();
        spuInfo.setCatalog3Id(catalog3Id);
        List<SpuInfo> spuInfoList = manageService.getSpuInfoList(spuInfo);
        return  spuInfoList;
    }

    /**
     * 实现文件上传。
     * @param file
     * @return
     * @throws IOException
     * @throws MyException
     */
    @ResponseBody
    @RequestMapping(value = "fileUpload",method = RequestMethod.POST)
    public String fileUpload(@RequestParam("file") MultipartFile file) throws IOException, MyException {

        String fileUploadPath = FastDfsUploadTemplate.getFileUploadPath(file, "/tracker.conf", "192.168.102.130");
        return fileUploadPath;
    }

    /**
     * 获取基本销售属性列表
     * @return
     */
    //http://localhost:8082/baseSaleAttrList
    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        System.err.println("我是。。。");
        return manageService.getBaseSaleAttrList();
    }

    /**
     * 添加与修改商品
     * @param spuInfo
     * @return
     */
    ////http://localhost:8082/saveSpuInfo
    @RequestMapping("saveSpuInfo")
    @ResponseBody
    public String saveSpuInfo(@RequestBody SpuInfo spuInfo){

        manageService.saveSpuInfo(spuInfo);

        return "ok";
    }

    /**
     * 获取某类商品的所有图片
     * @param spuId
     * @return
     */
    @RequestMapping("spuImageList")
    @ResponseBody
    public List<SpuImage> spuImageList(String spuId){
        return   manageService.getSpuImageList(spuId);
    }

    /**
     * 根据商品id获取该商品的销售属性列表
     * @param spuId
     * @return
     */
    //http://localhost:8082/spuSaleAttrList?spuId=66
     @RequestMapping("spuSaleAttrList")
     @ResponseBody
     public List<SpuSaleAttr> getspuSaleAttrList(String spuId){
        List<SpuSaleAttr> spuSaleAttrList = manageService.getSpuSaleAttrList(spuId);
        return  spuSaleAttrList;
     }

    /**
     * 添加或者修改商品库存信息。
     * @param skuInfo
     * @return
     */
    //http://localhost:8082/saveSkuInfo
    @RequestMapping("saveSkuInfo")
    @ResponseBody
    public String saveSkuInfo(@RequestBody  SkuInfo skuInfo){
        manageService.saveSkuInfo(skuInfo);
        return "OK";
    }

}