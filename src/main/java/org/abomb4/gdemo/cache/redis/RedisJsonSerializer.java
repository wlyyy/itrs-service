package org.abomb4.gdemo.cache.redis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Redis Json序列化工具，字符串采用UTF-8编码。
 * Json实现采用Gson库。
 *
 * @author abomb4
 */
public class RedisJsonSerializer implements GenericRedisCacheSerializer {

    private static final Charset CHARSET = Charset.forName("UTF-8");

    private final Gson gson;

    // initialization block
    {
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }

    @Override
    public <T> T deserialize(byte[] target, Class<T> type) throws RedisCacheSerializeException {
        final ByteArrayInputStream in = new ByteArrayInputStream(target);
        final InputStreamReader reader = new InputStreamReader(in, CHARSET);
        return gson.fromJson(reader, type);
    }

    @Override
    public byte[] serialize(Object target) throws RedisCacheSerializeException {
        final String json = gson.toJson(target);
        return json.getBytes(CHARSET);
    }
}
