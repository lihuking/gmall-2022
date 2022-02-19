package com.lihuking.gmall.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.lihuking.gmall.bean.UserAddress;
import com.lihuking.gmall.service.UserAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class OrderController {

   @Reference//远程调用注解
   private UserAddressService userAddressService;


    @RequestMapping("index")
    public String orderIndex(){

        return "index";
    }

    @ResponseBody
    @RequestMapping("address")
    public List<UserAddress> getUserAddressByUserId(@RequestParam("userId") String userId){
        System.err.println("我是....."+userAddressService);
        System.err.println("我是...."+userId);
        List<UserAddress> userAddressList = userAddressService.getUserAddressList(userId);

        return userAddressList;
    }

}
