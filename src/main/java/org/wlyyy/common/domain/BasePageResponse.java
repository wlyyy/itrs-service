package org.wlyyy.common.domain;

/**
 * 页面Rest接口返回结果基础类
 */
public abstract class BasePageResponse<T> {

    private final boolean success;
    private final String message;
    private final T data;

    public BasePageResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
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
}
