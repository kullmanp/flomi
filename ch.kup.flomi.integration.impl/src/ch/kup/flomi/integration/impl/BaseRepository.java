package ch.kup.flomi.integration.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ch.kup.flomi.integration.Repository;
 
public class BaseRepository<E,K> implements Repository<E, K> {
	protected EntityManager entityManager;
	protected Class<E> entityClass;
	
    @SuppressWarnings("unchecked")
	public BaseRepository() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass
                .getActualTypeArguments()[0];
	}
	
    @Override
    public List<E> findAll() {
        TypedQuery<E> query = entityManager.createQuery("select e from "
                + entityClass.getSimpleName() + " e", entityClass);
        return query.getResultList();
    }

    @Override
    public E findById(K id) {
        return entityManager.find(entityClass, id);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public E persist(E entity) {
        E merged = entityManager.merge(entity);
        return merged;
    }

    @Override
    public void remove(E entity) {
        entityManager.remove(entity);
    }

	
}
