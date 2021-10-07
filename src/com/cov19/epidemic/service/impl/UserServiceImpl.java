package com.cov19.epidemic.service.impl;

import com.cov19.epidemic.bean.UserInfo;
import com.cov19.epidemic.mapper.UserMapper;
import com.cov19.epidemic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserInfo findByAccount(UserInfo user) {
        return userMapper.findByAccount(user);
    }
}
