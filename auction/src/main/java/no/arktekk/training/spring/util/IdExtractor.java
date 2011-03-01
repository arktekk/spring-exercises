package no.arktekk.training.spring.util;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public interface IdExtractor<T> {
    Object key(T domainObject);
}
