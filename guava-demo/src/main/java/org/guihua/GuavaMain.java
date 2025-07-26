package org.guihua;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import java.util.concurrent.TimeUnit;

public class GuavaMain {

  /**
   * 创建一个缓存实例 该方法初始化一个具有初始容量和过期策略的缓存
   */
  public static Cache<Integer, Object> cache1() {
    // 创建一个缓存构建器，设置初始容量为1000，写入后100秒过期
    Cache<Integer, Object> cache =
        CacheBuilder.newBuilder()
            .maximumSize(3)
            .expireAfterWrite(1000, TimeUnit.SECONDS)
            .build();
    return cache;
  }

  public static Cache<Integer, Object> cache2() {
    // 创建一个缓存构建器，设置初始容量为1000，写入后100秒过期
    Cache<Integer, Object> cache =
        CacheBuilder.newBuilder()
            .initialCapacity(3)
            .expireAfterWrite(1000, TimeUnit.SECONDS)
            .recordStats()
            .build();
    return cache;
  }

  public static void main(String[] args) {
    Cache<Integer, Object> cache = cache2();
    cache.put(1, "one");
    cache.put(2, "two");
    cache.put(3, "three");

    Object object = cache.getIfPresent(1);
    System.out.println("Cache Value: " + object);

    object = cache.getIfPresent(2);
    System.out.println("Cache Value: " + object);

    cache.put(4, "four");

    System.out.println("Cache Size: " + cache.size());

    CacheStats cacheStats = cache.stats();
    System.out.println("Cache Stats: " + cacheStats);

    cache.asMap().keySet().stream().sorted().forEach(System.out::println);
  }
}
