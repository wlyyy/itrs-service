package org.wlyyy.common.domain;

/**
 * 页面Rest接口返回结果基础类
 */
public class BaseServiceResponse<T> {

    private final boolean success;
    private final String message;
    private final T data;
    private final Throwable cause;

    public BaseServiceResponse(boolean success, String message, T data, Throwable cause) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.cause = cause;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Throwable getCause() {
        return cause;
    }
}
