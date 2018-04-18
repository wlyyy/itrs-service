package org.wlyyy.itrs.dict;

/**
 * 字典啊
 *
 * @param <T> 字典code类型
 */
public interface IDictionary<T> {

    T getCode();

    String getName();

    String getDesc();
}
