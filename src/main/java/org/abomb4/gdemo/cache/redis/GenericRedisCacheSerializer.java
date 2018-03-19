package org.abomb4.gdemo.cache.redis;

/**
 * 支持泛型的Redis 缓存序列化器。
 * 这个序列化接口以byte[] 作为输入输出。
 */
public interface GenericRedisCacheSerializer {

    /**
     * 反序列化。
     *
     * @param target 目标对象
     * @param type 反序列化类型
     * @param <T> 反序列化类型
     * @return 反序列化结果
     * @throws RedisCacheSerializeException 序列化异常
     */
    <T> T deserialize(byte[] target, Class<T> type) throws RedisCacheSerializeException;

    /**
     * 序列化
     *
     * @param target 被序列化目标对象
     * @return byte[]对象
     * @throws RedisCacheSerializeException 序列化异常
     */
    byte[] serialize(Object target) throws RedisCacheSerializeException;
}
