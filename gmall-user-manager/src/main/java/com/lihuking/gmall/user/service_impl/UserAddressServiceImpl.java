package com.lihuking.gmall.user.service_impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lihuking.gmall.bean.UserAddress;
import com.lihuking.gmall.service.UserAddressService;
import com.lihuking.gmall.user.mapper.UserAddressMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Resource
    private UserAddressMapper userAddressMapper;

    /**
     * 获取用户地址。
     * @param userId 用户Id
     * @return 返回用户地址
     */
    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        return userAddressMapper.selectAll();
    }
}
