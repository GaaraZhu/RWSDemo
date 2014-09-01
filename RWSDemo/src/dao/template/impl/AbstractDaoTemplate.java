package dao.template.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.queries.QueryByExamplePolicy;
import org.eclipse.persistence.queries.ReadAllQuery;

import dao.template.DaoTemplate;

import util.transaction.EntityManagerHolder;

@SuppressWarnings("all")
public abstract class AbstractDaoTemplate<T> implements DaoTemplate<T> {

	public void push(T object) {
		getEntityManager().persist(object);
	}

	public T findById(int id) {
		return (T) getEntityManager().find(getTclass(), id);
	}

	public List<T> findByExample(T object) {
		QueryByExamplePolicy policy = new QueryByExamplePolicy();
		policy.excludeDefaultPrimitiveValues();
		ReadAllQuery q = new ReadAllQuery(object, policy);
		Query query = JpaHelper.createQuery(q, getEntityManager());
		return (List<T>) query.getResultList();
	}

	public List<T> findWithPaging(T object, int start, int limit) {
		QueryByExamplePolicy policy = new QueryByExamplePolicy();
		policy.excludeDefaultPrimitiveValues();
		ReadAllQuery q = new ReadAllQuery(object, policy);
		Query query = JpaHelper.createQuery(q, getEntityManager());
		query.setFirstResult(start);
		query.setMaxResults(start + limit);
		List<T> resultList = query.getResultList();
		return resultList;
	}

	public List<T> findAll() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> cquery = cb.createQuery(getTclass());
		cquery = cquery.select(cquery.from(getTclass()));
		return (List<T>) getEntityManager().createQuery(cquery).getResultList();
	}

	public synchronized Class getTclass() {
		Type genericType = this.getClass().getGenericSuperclass();
		return (Class) ((ParameterizedType) genericType)
				.getActualTypeArguments()[0];
	}

	public void update(T object) {
		getEntityManager().merge(object);
	}

	protected EntityManager getEntityManager() {
		return EntityManagerHolder.getInstance().getEntityManager();
	}

}
