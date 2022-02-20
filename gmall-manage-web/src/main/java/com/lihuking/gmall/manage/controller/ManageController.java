package com.lihuking.gmall.manage.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lihuking.gmall.bean.BaseAttrInfo;
import com.lihuking.gmall.bean.BaseCatalog1;
import com.lihuking.gmall.bean.BaseCatalog2;
import com.lihuking.gmall.bean.BaseCatalog3;
import com.lihuking.gmall.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ManageController {

    @Reference
    private ManageService manageService;

    @RequestMapping(value = "index" )
    public String index(){
        return "index";
    }


    @RequestMapping("getCatalog1")
    @ResponseBody
    public List<BaseCatalog1> getCatalog1(){
        return manageService.getCatalog1();
    }

    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<BaseCatalog2> getCatalog2(String catalog1Id){
        return manageService.getCatalog2(catalog1Id);
    }

    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<BaseCatalog3> getCatalog3(String catalog2Id){
        return manageService.getCatalog3(catalog2Id);
    }

    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<BaseAttrInfo> attrInfoList(String catalog3Id){
        return manageService.getAttrList(catalog3Id);
    }
}