package com.ohh.icloud.controller;

import com.ohh.icloud.common.result.ApiResult;
import com.ohh.icloud.service.SmsService;
import com.ohh.icloud.service.UserService;
import com.ohh.icloud.vo.RespUserVo;
import com.ohh.icloud.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/*用户管理*/
@RestController
@RequestMapping("/web/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Resource
    private SmsService smsService;


    @PostMapping("register")
    public ApiResult<Void> userRegister(UserVo userVo) {
        return ApiResult.ok();
    }

    @PostMapping("/userLogin")
    public ApiResult<RespUserVo> userLogin(@RequestBody UserVo userVo) {
        RespUserVo respUserVo = userService.userLogin(userVo);
        return ApiResult.ok(respUserVo);
    }

    @PostMapping("/sendCode/{phone}")
    public ApiResult<Void> sendCode(@PathVariable("phone")String phone) {
        smsService.sengCode(phone);
        return ApiResult.ok();
    }
}
