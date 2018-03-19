package org.abomb4.gdemo.cache.redis;

import java.nio.charset.Charset;

/**
 * 把byte和String互转的序列化器。
 * 如果在调用反序列化接口时，模板T不是String的话会抛出CastException异常。
 *
 * @author abomb4
 */
public class RedisUtf8StringSerializer implements GenericRedisCacheSerializer {

    private static final Charset CHARSET = Charset.forName("UTF-8");

    @Override
    public <T> T deserialize(byte[] target, Class<T> type) throws RedisCacheSerializeException {

        final T result;
        try {
            // noinspection unchecked
            result = (T) new String(target, CHARSET);
        } catch (RuntimeException e) {
            throw new RedisCacheSerializeException("Deserialization failed.", e);
        }
        return result;
    }

    @Override
    public byte[] serialize(Object target) throws RedisCacheSerializeException {
        String targetStr = target == null ? "null" : target.toString();
        return targetStr.getBytes(CHARSET);
    }
}
