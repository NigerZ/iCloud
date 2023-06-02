package com.ohh.icloud.exception;

import com.ohh.icloud.common.result.ApiResultCodeEnum;
import lombok.Data;

@Data
public class SmsExistException extends RuntimeException{

    private Integer code;
    private String msg;

    public SmsExistException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    public SmsExistException(ApiResultCodeEnum apiResultCodeEnum) {
        super(apiResultCodeEnum.getMessage());
        this.code = apiResultCodeEnum.getCode();
        this.msg = apiResultCodeEnum.getMessage();
    }


    @Override
    public String toString() {
        return "SmsExistException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
