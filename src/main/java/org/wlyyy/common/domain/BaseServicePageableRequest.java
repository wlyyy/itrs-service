package org.wlyyy.common.domain;

/**
 * 通用分页查询请求类
 */
public class BaseServicePageableRequest<T> {

    private final int pageNo;
    private final int pageSize;
    private final long total;
    private final T data;

    public BaseServicePageableRequest(int pageNo, int pageSize, long total, T data) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.data = data;
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

    public T getData() {
        return data;
    }
}
