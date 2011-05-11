package no.arktekk.training.spring.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Simple cache with no strategies for automatic evicting.
 * All handling of cache size must be done manually.
 *
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public class Cache<T> {
    private final ConcurrentMap<Object, T> cache = new ConcurrentHashMap<Object, T>();
    private int cacheHits = 0;
    private int cacheMiss = 0;


    public boolean contains(Object key) {
        return cache.containsKey(key);
    }


    public T get(Object key) {
        return cache.get(key);
    }

    public T get(Object key, Command<T> command) {
        if (!contains(key)) {
            cacheMiss++;
            T t = command.execute();
            if (t != null) {
                cache.put(key, t);
            }
        } else {
            cacheHits++;
        }
        return get(key);
    }

    public void evict(Object key) {
        cache.remove(key);
    }

    public void clear() {
        cache.clear();
    }

    public int cacheHits() {
        return cacheHits;
    }

    public int cacheMiss() {
        return cacheMiss;
    }
}
