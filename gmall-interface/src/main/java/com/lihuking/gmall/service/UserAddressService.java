package com.lihuking.gmall.service;

import com.lihuking.gmall.bean.UserAddress;

import java.util.List;

public interface UserAddressService {
     List<UserAddress> getUserAddressList(String userId);
}
