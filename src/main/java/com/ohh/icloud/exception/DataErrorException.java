package com.ohh.icloud.exception;

import com.ohh.icloud.common.result.ApiResultCodeEnum;
import lombok.Data;

/**
 * 数据错误异常
 */
@Data
public class DataErrorException extends RuntimeException{
    private Integer code;
    private String msg;

    public DataErrorException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public DataErrorException(ApiResultCodeEnum apiResultCodeEnum){
        super(apiResultCodeEnum.getMessage());
        this.code = apiResultCodeEnum.getCode();
        this.msg = apiResultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "DataErrorException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
