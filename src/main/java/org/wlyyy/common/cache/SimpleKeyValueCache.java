package org.wlyyy.common.cache;

/**
 * 简单KV缓存接口。
 *
 * @author wly
 */
public interface SimpleKeyValueCache {

    <T> T get(String key, Class<T> clazz);

    <T> void put(String key, T value, Class<T> clazz);

    void remove(String key);
}
