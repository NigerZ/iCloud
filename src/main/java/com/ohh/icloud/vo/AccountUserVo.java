package com.ohh.icloud.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountUserVo implements Serializable {
    // 用户账号
    private String username;
    // 用户密码
    private String password;
}
