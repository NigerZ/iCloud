package com.ohh.icloud.common.result;

import lombok.Getter;

@Getter
public enum ApiResultCodeEnum {
    SUCCESS(200,"成功"),
    FAIL(201, "失败"),
    SERVICE_ERROR(2012, "服务异常"),
    DATA_ERROR(204, "数据异常"),

    LOGIN_AUTH(208, "未登陆"),
    PERMISSION(209, "没有权限"),
    PARAMS_MISSING(401, "请求参数丢失"),
    FILE_UPLOAD_ERROR(301, "文件上传失败"),
    LOGIN_FAIL(501, "未登录"),
    SMS_EXIST(601, "30s内不能重复发送");
    ;

    private Integer code;

    private String message;

    private ApiResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
