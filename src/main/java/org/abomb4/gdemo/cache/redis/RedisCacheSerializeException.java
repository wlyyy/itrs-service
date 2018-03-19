package org.abomb4.gdemo.cache.redis;

/**
 * 序列化异常。
 *
 * @author abomb4
 */
public class RedisCacheSerializeException extends RuntimeException {

    static final long serialVersionUID = 1L;

    public RedisCacheSerializeException() {
        super();
    }

    public RedisCacheSerializeException(String message) {
        super(message);
    }

    public RedisCacheSerializeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisCacheSerializeException(Throwable cause) {
        super(cause);
    }

    protected RedisCacheSerializeException(String message, Throwable cause,
                                           boolean enableSuppression,
                                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
