package org.abomb4.gdemo.cache.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.codec.ByteArrayCodec;
import io.lettuce.core.codec.CompressionCodec;

import java.nio.charset.Charset;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import static org.abomb4.gdemo.utils.StringTemplateUtils.St;

/**
 * 缓存Redis Lettuce 实现。
 * Key序列化采用固定的String序列化工具。
 *
 * @author abomb4
 */
public class RedisKeyValueCacheLettuceImpl implements KeyValueCache {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(RedisKeyValueCacheLettuceImpl.class);

    private final RedisClient client;

    private final StatefulRedisConnection<byte[], byte[]> connection;

    private final Charset charset = Charset.forName("UTF-8");

    private final GenericRedisCacheSerializer valueSerializer;
    private final GenericRedisCacheSerializer keySerializer;

    public RedisKeyValueCacheLettuceImpl(String groupName) {
        if (groupName == null || groupName.equals("")) {
            throw new IllegalArgumentException("groupName cannot be null");
        }
        client = RedisClient.create("redis://localhost");

        connection = client.connect(CompressionCodec.valueCompressor(new ByteArrayCodec(), CompressionCodec.CompressionType.DEFLATE));

        valueSerializer = new RedisUtf8StringSerializer();
        keySerializer = new RedisJsonSerializer();
    }

    @Override
    public Object getNativeCache() {
        return client;
    }

    @Override
    public byte[] get(String key) {
        RedisCommands<byte[], byte[]> a = connection.sync();

        final byte[] realKey = makeRealKey(key);
        return a.get(realKey);
    }

    @Override
    public <T> T get(String key, Class<T> type) {
        final byte[] originValue = get(key);
        return valueSerializer.deserialize(originValue, type);
    }

    @Override
    public <T> T get(String key, Class<T> type, Callable<T> valueLoader) {
        final RedisCommands<byte[], byte[]> a = connection.sync();
        final byte[] realKey = makeRealKey(key);
        final byte[] cacheValue = a.get(realKey);

        if (cacheValue == null) {
            return null;
        } else {
            try {
                final T loadedValue = valueLoader.call();
            } catch (Exception e) {
                throw new CacheException(St.r("Call valueLoader [{}] failed!", valueLoader, e));
            }
        }
        return null;
    }

    @Override
    public <T> void put(String key, T value, Boolean async, BiFunction<String, Throwable, Object> callback) {
        if (async) {
            final RedisAsyncCommands<byte[], byte[]> a = connection.async();
            final byte[] realKey = makeRealKey(key);
            final byte[] realValue = valueSerializer.serialize(value);
            RedisFuture<String> future = a.set(realKey, realValue);
            future.handle(callback).whenComplete((result, exception) -> {
                if (exception != null) {
                    log.error("Put callback result: [{}], exception: [{}]", result, exception);
                } else {
                    log.debug("Put callback result: [{}]", result);
                }
            });
        } else {
            final RedisCommands<byte[], byte[]> a = connection.sync();
            final byte[] realKey = makeRealKey(key);
            final byte[] realValue = valueSerializer.serialize(value);
            a.set(realKey, realValue);
        }
    }

    @Override
    public void put(String key, Object value) {
    }

    @Override
    public void remove(String key) {

    }

    @Override
    public void clear() {

    }

    private byte[] makeRealKey(String key) {
        return keySerializer.serialize(key);
    }
}
