package ch.kup.flomi.integration;

import java.util.List;

/**
 * A general repository (ie DAO) with entity type and key type
 * 
 * @author kup
 * 
 * @param <E>
 * @param <K>
 */
public interface Repository<E, K> {
    List<E> findAll();

    E findById(K id);

    E persist(E entity);

    void remove(E entity);
}
