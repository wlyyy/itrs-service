package org.wlyyy.common.domain;

/**
 * 页面Rest接口返回结果基础类
 */
public class BaseServiceResponse<T> {

    /** 执行结果。判断类响应应该使用data作为判断结果而不是这个 */
    private final boolean success;
    /** 结果信息 */
    private final String message;
    /** 结果数据 */
    private final T data;
    /** 异常 */
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
