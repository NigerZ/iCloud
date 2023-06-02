package com.ohh.icloud.service;

public interface SmsService {

    /**
     * 发送登录验证码
     * @param phone
     */
    void sengCode(String phone);

    void sendSms(String phone, String msg);
}
