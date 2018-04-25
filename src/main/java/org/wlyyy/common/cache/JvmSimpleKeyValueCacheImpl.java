package org.wlyyy.common.cache;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于Java 线程安全Map的简单缓存，使用Json进行序列化
 *
 * @author wly
 */
public class JvmSimpleKeyValueCacheImpl implements SimpleKeyValueCache {

    private final Map<String, String> cacheMap = new ConcurrentHashMap<>();

    private final Gson gson = new GsonBuilder().create();

    @Override
    public <T> T get(String key, Class<T> clazz) {
        final String result = cacheMap.get(key);
        if (result == null) {
            return null;
        } else {
            return gson.fromJson(result, clazz);
        }
    }

    @Override
    public <T> void put(String key, T value, Class<T> clazz) {
        if (value == null) {
            cacheMap.put(key, null);
        } else {
            cacheMap.put(key, gson.toJson(value));
        }
    }

    @Override
    public void remove(String key) {
        cacheMap.remove(key);
    }
}
