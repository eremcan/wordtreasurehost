package com.word.repository.impl;

import com.word.domain.User;
import com.word.repository.ICommonDao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public class CommonDao<T, ID extends Serializable> implements ICommonDao<T, ID> {

	private final Class<T> persistentClass;

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public CommonDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public CommonDao(final Class<T> persistentClass) {
		super();
		this.persistentClass = persistentClass;
	}

	public void flush() {
		this.entityManager.flush();
	}

	@Override
	public int countAll() {
		return countByCriteria();
	}

	@Override
	public int countByExample(final T exampleInstance) {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());
		crit.setProjection(Projections.rowCount());
		crit.add(Example.create(exampleInstance));

		return (Integer) crit.list().get(0);
	}

	@Override
	public List<T> findAll() {
		return findByCriteria();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(final T exampleInstance) {
		Session session = (Session) getEntityManager().getDelegate();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		javax.persistence.criteria.CriteriaQuery<T> cq = cb.createQuery(getEntityClass());
		Root<T> rt = cq.from(getEntityClass());
		ParameterExpression<T> pe = cb.parameter(getEntityClass());
		cq.select(rt).where(cb.equal(pe.));
		return query.getResultList();

		/*
		Criteria crit = session.createCriteria(getEntityClass());
		Example example = Example.create(exampleInstance);
		crit.add(example);
		crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return crit.list();*/
	}

	@Override
	public T findById(final ID id) {
		return getEntityManager().find(persistentClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQuery(final String name, Object... params) {
		javax.persistence.Query query = getEntityManager().createNamedQuery(
				name);

		for (int i = 0; i < params.length; i++) {
			query.setParameter(i + 1, params[i]);
		}

		return (List<T>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByNamedQueryAndNamedParams(final String name,
												  final Map<String, ?> params) {
		javax.persistence.Query query = getEntityManager().createNamedQuery(
				name);

		for (final Map.Entry<String, ?> param : params
				.entrySet()) {
			query.setParameter(param.getKey(), param.getValue());
		}

		return (List<T>) query.getResultList();
	}

	@Override
	public Class<T> getEntityClass() {
		return persistentClass;
	}

	@Required
	@PersistenceContext
	public void setEntityManager(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public List<T> findByCriteria(String alias, final Order[] orders, final Criterion... criterion) {
		return findByCriteria(alias, -1, -1, orders, criterion);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(String alias, final int firstResult,
			final int maxResults, final Order[] orders, final Criterion... criterion) {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass(), alias);

		for (final Criterion c : criterion) {
			crit.add(c);
		}
		
		for (final Order o : orders) {
			crit.addOrder(o);
		}

		if (firstResult > 0) {
			crit.setFirstResult(firstResult);
		}

		if (maxResults > 0) {
			crit.setMaxResults(maxResults);
		}
		crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}
	
	public List<T> findByCriteria(final Order[] orders, final Criterion... criterion) {
		return findByCriteria(-1, -1, orders, criterion);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final int firstResult, final int maxResults,
			final Order[] orders, final Criterion... criterion) {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());

		for (final Criterion c : criterion) {
			crit.add(c);
		}
		
		for (final Order o : orders) {
			crit.addOrder(o);
		}

		if (firstResult > 0) {
			crit.setFirstResult(firstResult);
		}

		if (maxResults > 0) {
			crit.setMaxResults(maxResults);
		}
		crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}
	
	public List<T> findByCriteria(String alias, final Criterion... criterion) {
		return findByCriteria(alias, -1, -1, criterion);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(String alias, final int firstResult,
								  final int maxResults, final Criterion... criterion) {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass(), alias);

		for (final Criterion c : criterion) {
			crit.add(c);
		}

		if (firstResult > 0) {
			crit.setFirstResult(firstResult);
		}

		if (maxResults > 0) {
			crit.setMaxResults(maxResults);
		}
		crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}

	public List<T> findByCriteria(ProjectionList projections, final Criterion... criterion) {
		return findByCriteria(-1, -1, projections, criterion);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final int firstResult,
								  final int maxResults, final ProjectionList projections,
								  final Criterion... criterion) {

		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());
		crit.setProjection(projections);
		crit.setResultTransformer(Transformers.aliasToBean(getEntityClass()));
		for (final Criterion c : criterion) {
			crit.add(c);
		}

		if (firstResult > 0) {
			crit.setFirstResult(firstResult);
		}

		if (maxResults > 0) {
			crit.setMaxResults(maxResults);
		}

		return crit.list();
	}

	public List<T> findByCriteria(final Criterion... criterion) {
		return findByCriteria(-1, -1, criterion);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(final int firstResult,
								  final int maxResults, final Criterion... criterion) {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());

		for (final Criterion c : criterion) {
			crit.add(c);
		}

		if (firstResult > 0) {
			crit.setFirstResult(firstResult);
		}

		if (maxResults > 0) {
			crit.setMaxResults(maxResults);
		}
		crit.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		return crit.list();
	}

	public int countByCriteria(Criterion... criterion) {
		Session session = (Session) getEntityManager().getDelegate();
		Criteria crit = session.createCriteria(getEntityClass());
		crit.setProjection(Projections.rowCount());

		for (final Criterion c : criterion) {
			crit.add(c);
		}

		return (Integer) crit.list().get(0);
	}

	@Override
	public void delete(T entity) {
		getEntityManager().remove(entity);
	}

	@Override
	public void merge(T entity) {
		getEntityManager().merge(entity);
	}

	@Override
	public void persist(T entity) {
		getEntityManager().persist(entity);
	}
}