package com.cov19.epidemic.service;

import com.cov19.epidemic.bean.UserInfo;

public interface UserService {
    //根据用户名和密码登录
    UserInfo findByAccount(UserInfo user);
}
