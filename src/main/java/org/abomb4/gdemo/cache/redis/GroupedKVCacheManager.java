package org.abomb4.gdemo.cache.redis;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.Set;

/**
 * 分组KV缓存，主要用于clear方法，分组可以实现清除指定的批量数据而不是所有缓存
 *
 * @author abomb4
 */
public interface GroupedKVCacheManager {

    /**
     * 获取一个kv缓存组
     *
     * @param groupName 分组名
     * @return kv缓存接口
     */
    KeyValueCache getCache(String groupName);

    /**
     * 获取当前Manager中所有缓存的组名
     *
     * @return 一堆组名
     */
    Set<String> getNames();


    /**
     * 清空某组缓存
     *
     * @param groupName 组名
     */
    void clear(String groupName);
}
