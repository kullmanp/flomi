package ch.kup.flomi.integration.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ch.kup.flomi.domain.Identifiable;
import ch.kup.flomi.integration.Repository;

public class BaseRepository<E extends Identifiable<K>, K> implements
		Repository<E, K> {
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
	public E save(E entity) {
		if (entity.getId() == null) {
			entityManager.persist(entity);
			return entity;
		} else {
			return entityManager.merge(entity);
		}
	}

	@Override
	public void remove(E entity) {
		entityManager.remove(entity);
	}

}
