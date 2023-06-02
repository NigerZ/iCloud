package com.ohh.icloud.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ohh.icloud.common.result.ApiResultCodeEnum;
import com.ohh.icloud.constant.RedisConstant;
import com.ohh.icloud.constant.SysConstant;
import com.ohh.icloud.entity.User;
import com.ohh.icloud.exception.DataErrorException;
import com.ohh.icloud.exception.LoginFailedException;
import com.ohh.icloud.exception.ParamMissingException;
import com.ohh.icloud.mapper.UserMapper;
import com.ohh.icloud.service.UserService;
import com.ohh.icloud.utils.JwtUtil;
import com.ohh.icloud.utils.MD5;
import com.ohh.icloud.utils.RedisUtil;
import com.ohh.icloud.vo.AccountUserVo;
import com.ohh.icloud.vo.RespUserVo;
import com.ohh.icloud.vo.SmsUserVo;
import com.ohh.icloud.vo.UserVo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public RespUserVo userLogin(UserVo userVo) {
        SmsUserVo smsUserVo = userVo.getSmsUserVo();
        AccountUserVo accountUserVo = userVo.getAccountUserVo();
        if (!BeanUtil.isEmpty(smsUserVo)) {
            return smsUserLogin(smsUserVo);
        }
        if (!BeanUtil.isEmpty(accountUserVo)) {
            return accountUserLogin(accountUserVo);
        }
        return null;
    }

    private RespUserVo smsUserLogin(SmsUserVo smsUserVo) {

        String phone = smsUserVo.getPhone();
        String code = smsUserVo.getCode();
        if (code == null)
            // 验证码校验
            throw new ParamMissingException(ApiResultCodeEnum.PARAMS_MISSING.getCode(), ApiResultCodeEnum.PARAMS_MISSING.getMessage());
        // 校验验证码
        // 从redis中获取验证码对比
        String redisCodeKey = RedisConstant.CODE_KEY + phone;
        String redisCode = (String) redisTemplate.opsForValue().get(redisCodeKey);
        if (!code.equals(redisCode))
            // 定义一个验证码错误异常
            throw new DataErrorException(ApiResultCodeEnum.DATA_ERROR.getCode(), ApiResultCodeEnum.DATA_ERROR.getMessage());

        User user = baseMapper.getUserByPhone(phone);
        if (user == null) {
            // 用户不存在时注册用户
            user = new User();
            user.setPhone(phone)
                    .setPassword(MD5.encrypt(SysConstant.DEFAULT_PASSWORD))
                    .setUsername(phone)
                    .setNickname(phone);
            baseMapper.insert(user);
        }


        RespUserVo respUserVo = new RespUserVo();
        String token = JwtUtil.generateToken(user.getId(), user.getUsername());
        BeanUtil.copyProperties(user, respUserVo);
        respUserVo.setToken(token);
        setTokenInRedis(respUserVo);
        redisTemplate.delete(redisCodeKey);
        return respUserVo;
    }

    private RespUserVo accountUserLogin(AccountUserVo accountUserVo) {
        String username = accountUserVo.getUsername();
        String password = accountUserVo.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            // 参数缺失错误
            throw new ParamMissingException(ApiResultCodeEnum.PARAMS_MISSING.getCode(), ApiResultCodeEnum.DATA_ERROR.getMessage());
        }
        User user = baseMapper.getUserByUsername(username);
        if (user != null) {
            String MD5Password = MD5.encrypt(password);
            if (!MD5Password.equals(user.getPassword())) {
                // 登录失败
                throw new LoginFailedException(ApiResultCodeEnum.LOGIN_FAIL.getCode(), ApiResultCodeEnum.LOGIN_FAIL.getMessage());
            }
        } else {
            user = new User();
            user.setPassword(MD5.encrypt(password))
                    .setUsername(username)
                    .setNickname(username);
            baseMapper.insert(user);
        }
        RespUserVo respUserVo = new RespUserVo();
        String token = JwtUtil.generateToken(user.getId(), user.getUsername());
        BeanUtil.copyProperties(user, respUserVo);
        respUserVo.setToken(token);
        setTokenInRedis(respUserVo);
        return respUserVo;
    }

    private void setTokenInRedis(RespUserVo respUserVo) {
        String username = respUserVo.getUsername();
        String key = RedisConstant.TOKEN_KEY + username;
        redisUtil.setStringRedisWithExpire(key, respUserVo.getToken(), RedisConstant.TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
    }
}
