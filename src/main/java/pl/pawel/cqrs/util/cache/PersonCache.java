package pl.pawel.cqrs.util.cache;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.cache.caffeine.CaffeineCache;

public class PersonCache extends CaffeineCache {

  public PersonCache(String name,
      Cache<Object, Object> cache, boolean allowNullValues) {
    super(name, cache, allowNullValues);
  }
}
