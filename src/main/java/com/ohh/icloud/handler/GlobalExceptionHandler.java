package com.ohh.icloud.handler;

import com.ohh.icloud.common.result.ApiResult;
import com.ohh.icloud.exception.*;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局统一处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    public ApiResult SmsExistError(SmsExistException e) {
        e.printStackTrace();
        return ApiResult.fail().code(e.getCode()).message(e.getMsg());
    }

    @ExceptionHandler(LoginFailedException.class)
    @ResponseBody
    public ApiResult LoginFailedError(LoginFailedException e){
        e.printStackTrace();
        return ApiResult.fail().code(e.getCode()).message(e.getMsg());
    }

    @ExceptionHandler(DataErrorException.class)
    @ResponseBody
    public ApiResult DataError(DataErrorException e) {
        e.printStackTrace();
        return ApiResult.fail().code(e.getCode()).message(e.getMsg());
    }

    @ExceptionHandler(ParamMissingException.class)
    @ResponseBody
    public ApiResult paramMissError(ParamMissingException e) {
        e.printStackTrace();
        return ApiResult.fail().code(e.getCode()).message(e.getMsg());
    }

    @ExceptionHandler(ICloudException.class)
    @ResponseBody
    public ApiResult error(ICloudException e) {
        e.printStackTrace();
        return ApiResult.fail().message(e.getMsg()).code(e.getCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiResult error(Exception e) {
        e.printStackTrace();
        return ApiResult.fail();
    }
}
