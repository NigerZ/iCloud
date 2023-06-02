package com.ohh.icloud.service.impl;

import com.ohh.icloud.common.result.ApiResultCodeEnum;
import com.ohh.icloud.constant.RedisConstant;
import com.ohh.icloud.exception.ParamMissingException;
import com.ohh.icloud.exception.SmsExistException;
import com.ohh.icloud.service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void sengCode(String phone) {

        // 参数校验
        if (StringUtils.isEmpty(phone))
            throw new ParamMissingException(ApiResultCodeEnum.PARAMS_MISSING.getCode(), ApiResultCodeEnum.PARAMS_MISSING.getMessage());
        String redisCodeKey = RedisConstant.CODE_KEY + phone;
        // 检测该手机号码最近5分钟是否已发送过验证码(防止短信盗刷）
        String code = (String) redisTemplate.opsForValue().get(redisCodeKey);
        if (!StringUtils.isEmpty(code))
            // 验证码已发送全局异常
            throw new SmsExistException(ApiResultCodeEnum.SMS_EXIST.getCode(), ApiResultCodeEnum.SMS_EXIST.getMessage());


        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int randomNum = random.nextInt(10);
            stringBuffer.append(randomNum);
        }
        // 发送验证码
        this.sendSms(phone, stringBuffer.toString());
        //  将验证码保存到redis中
        redisTemplate.opsForValue().set(redisCodeKey, stringBuffer.toString());
        redisTemplate.expire(redisCodeKey, RedisConstant.CODE_EXPIRE_TIME, TimeUnit.SECONDS);
    }

    @Override
    public void sendSms(String phone, String msg) {
        // todo 对接短信服务
    }
}
