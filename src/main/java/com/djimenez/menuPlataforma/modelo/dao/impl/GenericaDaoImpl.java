package com.djimenez.menuPlataforma.modelo.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import com.djimenez.menuPlataforma.modelo.dao.GenericaDao;

public class GenericaDaoImpl<T>  implements GenericaDao<T>{

	private Class<T> entityClass;

	protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("menuPlataforma_PU");
	protected EntityManager entityManager;

	// constructoresGenerica
	public GenericaDaoImpl() {
		entityManager = emf.createEntityManager();
	}

	public GenericaDaoImpl(Class<T> entityClass) {
		this.entityClass = entityClass;
		entityManager = emf.createEntityManager();
	}

	// definirCrud
	public T create(T t) {
		this.entityManager.persist(t);
		return t;
	}

	public T read(Object id) {
		return this.entityManager.find(entityClass, id);
	}

	public T update(T t) {
		return this.entityManager.merge(t);
	}

	public void delete(T t) {
		t = this.entityManager.merge(t);
		this.entityManager.remove(t);
	}

	// definirParametros
	public void beginTransaction() {
		if (!entityManager.getTransaction().isActive())
			entityManager = emf.createEntityManager();

		entityManager.getTransaction().begin();
	}

	public void commit() {
		if (entityManager.getTransaction().isActive())
			entityManager.getTransaction().commit();
	}

	public void rollback() {
		if (entityManager.getTransaction().isActive())
			entityManager.getTransaction().rollback();
	}

	public void closeTransaction() {
		entityManager.close();
	}

	public void commitAndCloseTransaction() {
		commit();
		closeTransaction();
	}

	public void flush() {
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		CriteriaQuery<Object> cq = this.entityManager.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return (List<T>) this.entityManager.createQuery(cq).getResultList();
	}
}
