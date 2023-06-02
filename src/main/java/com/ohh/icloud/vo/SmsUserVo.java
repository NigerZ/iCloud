package com.ohh.icloud.vo;

import lombok.Data;

import java.io.Serializable;

/*用户短信登录*/
@Data
public class SmsUserVo implements Serializable {
    // 手机号码
    private String phone;
    // 验证码
    private String code;
}
