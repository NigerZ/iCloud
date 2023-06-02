package com.ohh.icloud.exception;

import com.ohh.icloud.common.result.ApiResultCodeEnum;
import lombok.Data;

@Data
public class ICloudException extends RuntimeException{

    private Integer code;
    private String msg;

    public ICloudException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ICloudException(ApiResultCodeEnum apiResultCodeEnum) {
        super(apiResultCodeEnum.getMessage());
        this.code = apiResultCodeEnum.getCode();
        this.msg = apiResultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "ICloudException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
