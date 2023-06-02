package com.ohh.icloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ohh.icloud.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据手机号码获取用户信息
     * @param phone 手机号码
     * @return 用户信息
     */
    User getUserByPhone(@Param("phone") String phone);

    User getUserByUsername(@Param("username") String username);
}
