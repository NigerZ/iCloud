package com.ohh.icloud.exception;

import com.ohh.icloud.common.result.ApiResultCodeEnum;
import lombok.Data;

/**
 * 请求参数缺失异常
 */
@Data
public class ParamMissingException extends RuntimeException {

    private Integer code;
    private String msg;

    public ParamMissingException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
    public ParamMissingException(ApiResultCodeEnum apiResultCodeEnum) {
        super(apiResultCodeEnum.getMessage());
        this.code = apiResultCodeEnum.getCode();
        this.msg = apiResultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "ParamMissingException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
