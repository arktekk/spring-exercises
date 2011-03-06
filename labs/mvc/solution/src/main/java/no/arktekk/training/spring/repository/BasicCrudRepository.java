package no.arktekk.training.spring.repository;

import java.util.List;

/**
 * @author <a href="mailto:kaare.nilsen@arktekk.no">Kaare Nilsen</a>
 */
public interface BasicCrudRepository<T> {
    T find(Object id);

    List<T> list();

    void addNew(T domainObject);

    void update(T domainObject);

    void delete(T domainObject);
}
