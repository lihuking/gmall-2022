package com.lihuking.gmall.user.service_impl;

import com.lihuking.gmall.bean.UserInfo;
import com.lihuking.gmall.service.UserInfoService;
import com.lihuking.gmall.user.mapper.UserInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    public List<UserInfo> getuUserInfoAll() {
        return userInfoMapper.selectAll();
    }
}