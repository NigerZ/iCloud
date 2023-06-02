package com.ohh.icloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ohh.icloud.entity.User;
import com.ohh.icloud.vo.RespUserVo;
import com.ohh.icloud.vo.UserVo;

public interface UserService extends IService<User> {
    /**
     * 用户登录
     *
     * @param userVo 表单数据
     * @return
     */
    RespUserVo userLogin(UserVo userVo);
}
