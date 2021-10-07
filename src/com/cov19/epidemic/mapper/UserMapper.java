package com.cov19.epidemic.mapper;

import com.cov19.epidemic.bean.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select(value = "SELECT u.user_id,u.account,u.password,u.user_name FROM users u " +
            "WHERE u.account=#{account} AND u.password =#{password} AND u.del_flag = 0")
    UserInfo findByAccount(UserInfo user);
}
