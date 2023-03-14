package com.ebs.custom_table.result;

/**
 * @param <T>
 * @author laohui
 * @desc 响应结果封装，会由切面拦截更改状态码
 */
public class ResultResponse<T> {
    private Integer code;
    private String message;
    private T data;
    private long timestamp;

    private ResultResponse() {
        timestamp = System.currentTimeMillis();
    }

    public static <T> ResultResponse<T> success(T data) {
        ResultResponse<T> response = new ResultResponse<>();
        response.setCode(ResultCode.RC200.getCode());
        response.setMessage(ResultCode.RC200.getMessage());
        response.setData(data);
        return response;
    }

    public static <T> ResultResponse<T> success(String message, T data) {
        ResultResponse<T> response = new ResultResponse<>();
        response.setCode(ResultCode.RC200.getCode());
        response.setMessage(message);
        response.setData(data);
        return response;
    }

    public static <T> ResultResponse<T> error(Integer httpStatusCode, String message) {
        ResultResponse<T> response = new ResultResponse<>();
        response.setCode(httpStatusCode);
        response.setMessage(message);
        return response;
    }

    public static <T> ResultResponse<T> error(Integer httpStatusCode, T data) {
        ResultResponse<T> response = new ResultResponse<>();
        response.setCode(httpStatusCode);
        response.setData(data);
        return response;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
