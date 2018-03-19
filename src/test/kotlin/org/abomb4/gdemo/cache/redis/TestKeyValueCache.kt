package org.abomb4.gdemo.cache.redis

import org.junit.Test
import java.util.function.BiFunction


class TestKeyValueCache {

    @Test
    fun test() {
        val cache: KeyValueCache = RedisKeyValueCacheLettuceImpl("")

        cache.put<String>(
                "",
                "",
                true,
                BiFunction { t: Any, u: Throwable -> true }
        )
    }
}