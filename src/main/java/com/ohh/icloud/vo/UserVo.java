package com.ohh.icloud.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {

    // 短信登陆
    private SmsUserVo smsUserVo;
    // 账号密码登录

    private AccountUserVo accountUserVo;
}
