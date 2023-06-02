package com.ohh.icloud.constant;
/*redis的常量*/
public class RedisConstant {
    // 验证码过期时间（5分钟）
    public static Long CODE_EXPIRE_TIME = 300L;
    // 验证码
    public static String CODE_KEY = "iCloud:code:";

    // token的key
    public static String TOKEN_KEY = "iCloud:token:";
    // token过期时间
    public static Long TOKEN_EXPIRE_TIME = 86400l;
}
