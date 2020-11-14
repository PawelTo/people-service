package pl.pawel.cqrs.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static java.time.Duration.ofMinutes;
import static java.time.Duration.ofSeconds;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    public static final String NAMES = "NAMES";
    public static final String NUMBERS = "NUMBERS";

    private final SimpleCacheManager cacheManager = new SimpleCacheManager();
    private final List<Cache> caches = new ArrayList<>();

    @Bean
    @Override
    public CacheManager cacheManager() {
        addCache(NAMES, ofMinutes(10));
        addCache(NUMBERS, ofSeconds(30));
        return cacheManager;
    }

    private void addCache(String cacheName, Duration expireAfterWrite) {
        CaffeineCache caffeineCache = new CaffeineCache(cacheName, Caffeine
                                                                           .newBuilder()
                                                                           .expireAfterWrite(expireAfterWrite)
                                                                           .build());
        addCache(caffeineCache);
    }

    public void addCache(Cache cache) {
        caches.add(cache);
        cacheManager.setCaches(caches);
        cacheManager.initializeCaches();
    }
}
