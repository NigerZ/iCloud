package com.ohh.icloud.common.result;

import lombok.Data;

@Data
public class ApiResult<T> {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 返回错误信息
     */
    private String message;

    public ApiResult() {
    }

    // 返回数据
    protected static <T> ApiResult<T> build(T data) {
        ApiResult<T> ApiResult = new ApiResult<T>();
        if (data != null)
            ApiResult.setData(data);
        return ApiResult;
    }
    public static <T> ApiResult<T> build(T body, Integer code, String message) {
        ApiResult<T> ApiResult = build(body);
        ApiResult.setCode(code);
        ApiResult.setMessage(message);
        return ApiResult;
    }

    public static <T> ApiResult<T> build(T body, ApiResultCodeEnum ApiResultCodeEnum) {
        ApiResult<T> ApiResult = build(body);
        ApiResult.setCode(ApiResultCodeEnum.getCode());
        ApiResult.setMessage(ApiResultCodeEnum.getMessage());
        return ApiResult;
    }

    public static<T> ApiResult<T> ok(){
        return ApiResult.ok(null);
    }

    /**
     * 操作成功
     * @param data  baseCategory1List
     * @param <T>
     * @return
     */
    public static<T> ApiResult<T> ok(T data){
        ApiResult<T> ApiResult = build(data);
        return build(data, ApiResultCodeEnum.SUCCESS);
    }

    public static<T> ApiResult<T> fail(){
        return ApiResult.fail();
    }

    /**
     * 操作失败
     * @param data
     * @param <T>
     * @return
     */
    public static<T> ApiResult<T> fail(T data){
        ApiResult<T> ApiResult = build(data);
        return build(data, ApiResultCodeEnum.FAIL);
    }

    public ApiResult<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public ApiResult<T> code(Integer code){
        this.setCode(code);
        return this;
    }
}
