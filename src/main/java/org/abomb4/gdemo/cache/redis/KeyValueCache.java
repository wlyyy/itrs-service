package org.abomb4.gdemo.cache.redis;

import java.util.concurrent.Callable;
import java.util.function.BiFunction;

/**
 * KV缓存简单抽象，限定KEY值为String 类型
 */
public interface KeyValueCache {

    /**
     * 返回Cache提供者的原始API.
     */
    Object getNativeCache();

    /**
     * 从缓存获取，默认以byte串的形式返回
     *
     * @param key key
     * @return value
     */
    byte[] get(String key);

    /**
     * 带序列化方式的GET方法
     *
     * @param key  key
     * @param type 数据类型Class对象
     * @param <T>  数据类型
     * @return 指定数据类型的值
     */
    <T> T get(String key, Class<T> type);

    /**
     * 若缓存中存在，则从缓存取；否则执行valueLoader，缓存执行结果并返回结果。
     *
     * @param key         key
     * @param type        值类型
     * @param valueLoader 造值方法
     * @return 缓存值或valueLoader返回值
     */
    <T> T get(String key, Class<T> type, Callable<T> valueLoader);

    /**
     * 放值。如果已有原值，则会覆盖。
     *
     * @param key      key
     * @param value    value
     * @param async    是否异步
     * @param callback 放值成功回调。回调时会传入2个参数，String的result和Throwable异常，都是可能为空的
     * @param <T>      value类型
     */
    <T> void put(String key, T value, Boolean async, BiFunction<String, Throwable, Object> callback);

    /**
     * 同步放值。如果已有原值，则会覆盖。
     *
     * @param key   key
     * @param value value
     */
    void put(String key, Object value);

    /**
     * 清空某值
     *
     * @param key key
     */
    void remove(String key);

    /**
     * 清空所有缓存
     */
    void clear();

}

