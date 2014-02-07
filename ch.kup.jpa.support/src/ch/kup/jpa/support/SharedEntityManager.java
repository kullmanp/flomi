package ch.kup.jpa.support;

import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.Metamodel;

import aQute.bnd.annotation.component.Component;
import aQute.bnd.annotation.component.Reference;

/**
 * An EntityManager that can be injected in repositories. It delegates to a real
 * EntityManager per operation. This is similar to Spring's SharedEntityManager
 * or to Peter Kriens' TransactionalEntityManager.
 */
@Component
public class SharedEntityManager implements EntityManager {
	private EntityManagerFactory emf;

	final ThreadLocal<EntityManager> perThreadEntityManager = new ThreadLocal<EntityManager>();

	volatile boolean open = true;

	@Override
	public void clear() {
		getEM().clear();
	}

	@Override
	public void close() {
		getEM().close();
		perThreadEntityManager.remove();
	}

	@Override
	public boolean contains(Object arg0) {
		return getEM().contains(arg0);
	}

	@Override
	public Query createNamedQuery(String arg0) {
		return getEM().createNamedQuery(arg0);
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(String arg0, Class<T> arg1) {
		return getEM().createNamedQuery(arg0, arg1);
	}

	@Override
	public Query createNativeQuery(String arg0) {
		return getEM().createNativeQuery(arg0);
	}

	@Override
	public Query createNativeQuery(String arg0,
			@SuppressWarnings("rawtypes") Class arg1) {
		return getEM().createNativeQuery(arg0, arg1);
	}

	@Override
	public Query createNativeQuery(String arg0, String arg1) {
		return getEM().createNativeQuery(arg0, arg1);
	}

	@Override
	public <T> TypedQuery<T> createQuery(CriteriaQuery<T> arg0) {
		return getEM().createQuery(arg0);
	}

	@Override
	public Query createQuery(String arg0) {
		return getEM().createQuery(arg0);
	}

	@Override
	public <T> TypedQuery<T> createQuery(String arg0, Class<T> arg1) {
		return getEM().createQuery(arg0, arg1);
	}

	@Override
	public void detach(Object arg0) {
		getEM().detach(arg0);
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1) {
		return getEM().find(arg0, arg1);
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, LockModeType arg2) {
		return getEM().find(arg0, arg1, arg2);
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, LockModeType arg2,
			Map<String, Object> arg3) {
		return getEM().find(arg0, arg1, arg2, arg3);
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, Map<String, Object> arg2) {
		return getEM().find(arg0, arg1, arg2);
	}

	@Override
	public void flush() {
		getEM().flush();
	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return getEM().getCriteriaBuilder();
	}

	@Override
	public Object getDelegate() {
		return getEM().getDelegate();
	}

	/**
	 * The delegated methods call this method to get the delegate. This method
	 * verifies if we're still open, if there already is an Entity Manager for
	 * this thread and otherwise creates it and enlists it for auto close at the
	 * current transaction.
	 * 
	 * @return an Entity Manager
	 */
	private EntityManager getEM() throws IllegalStateException {
		if (!open)
			throw new IllegalStateException("The JPA bridge has closed");

		EntityManager em = perThreadEntityManager.get();
		if (em != null)
			return em;

		em = emf.createEntityManager();
		perThreadEntityManager.set(em);

		return em;
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		return getEM().getEntityManagerFactory();
	}

	@Override
	public FlushModeType getFlushMode() {
		return getEM().getFlushMode();
	}

	@Override
	public LockModeType getLockMode(Object arg0) {
		return getEM().getLockMode(arg0);
	}

	@Override
	public Metamodel getMetamodel() {
		return getEM().getMetamodel();
	}

	@Override
	public Map<String, Object> getProperties() {
		return getEM().getProperties();
	}

	@Override
	public <T> T getReference(Class<T> arg0, Object arg1) {
		return getEM().getReference(arg0, arg1);
	}

	@Override
	public EntityTransaction getTransaction() {
		return getEM().getTransaction();
	}

	@Override
	public boolean isOpen() {
		return getEM().isOpen();
	}

	@Override
	public void joinTransaction() {
		getEM().joinTransaction();
	}

	@Override
	public void lock(Object arg0, LockModeType arg1) {
		getEM().lock(arg0, arg1);
	}

	@Override
	public void lock(Object arg0, LockModeType arg1, Map<String, Object> arg2) {
		getEM().lock(arg0, arg1, arg2);
	}

	@Override
	public <T> T merge(T arg0) {
		return getEM().merge(arg0);
	}

	@Override
	public void persist(Object arg0) {
		getEM().persist(arg0);
	}

	@Override
	public void refresh(Object arg0) {
		getEM().refresh(arg0);
	}

	@Override
	public void refresh(Object arg0, LockModeType arg1) {
		getEM().refresh(arg0, arg1);
	}

	@Override
	public void refresh(Object arg0, LockModeType arg1, Map<String, Object> arg2) {
		getEM().refresh(arg0, arg1, arg2);
	}

	@Override
	public void refresh(Object arg0, Map<String, Object> arg1) {
		getEM().refresh(arg0, arg1);
	}

	@Override
	public void remove(Object arg0) {
		getEM().remove(arg0);
	}

	@Reference
	public void setEMF(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public void setFlushMode(FlushModeType arg0) {
		getEM().setFlushMode(arg0);
	}

	@Override
	public void setProperty(String arg0, Object arg1) {
		getEM().setProperty(arg0, arg1);
	}

	void shutdown() {
		open = false;
	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		return getEM().unwrap(arg0);
	}
}
