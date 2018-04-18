package org.wlyyy.common.domain;

import java.util.List;

/**
 * 页面Rest接口带!分页的返回结果基础类
 */
public class BaseRestPageableResponse<T> {

    private final boolean success;
    private final String message;
    private final List<T> datas;
    private final int pageNo;
    private final int pageSize;
    private final long total;

    public BaseRestPageableResponse(boolean success, String message, List<T> datas, int pageNo, int pageSize, long total) {
        this.success = success;
        this.message = message;
        this.datas = datas;
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
