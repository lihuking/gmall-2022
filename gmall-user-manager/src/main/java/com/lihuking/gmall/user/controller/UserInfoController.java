package com.lihuking.gmall.user.controller;

import com.lihuking.gmall.bean.UserInfo;
import com.lihuking.gmall.service.UserInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @RequestMapping("findAll")
    @ResponseBody
    public List<UserInfo> findAll(){
        List<UserInfo> userInfoList = userInfoService.getuUserInfoAll();
        return userInfoList;
    }
}