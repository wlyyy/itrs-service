package org.wlyyy.common.domain;

import java.util.List;

/**
 * 页面Rest接口带!分页的返回结果基础类
 */
public class BaseServicePageableResponse<T> {

    private final boolean success;
    private final String message;
    private final List<T> datas;
    private final Throwable cause;
    private final int pageNo;
    private final int pageSize;
    private final long total;

    public BaseServicePageableResponse(boolean success, String message, List<T> datas, Throwable cause,
                                       int pageNo, int pageSize, long total) {
        this.success = success;
        this.message = message;
        this.datas = datas;
        this.cause = cause;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getDatas() {
        return datas;
    }

    public Throwable getCause() {
        return cause;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotal() {
        return total;
    }
}
