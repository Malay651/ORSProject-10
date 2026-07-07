package com.rays.common;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Abstract base implementation of {@link BaseDAOInt} providing common JPA-based
 * data access operations for all DAO classes in the application.
 * <p>
 * Concrete DAO implementations must extend this class and provide the specific
 * DTO class type and the WHERE clause predicates for custom search queries.
 * </p>
 *
 * @param <T> the DTO type extending {@link BaseDTO} that this DAO manages
 *
 * @author malay dongre
 */
public abstract class BaseDAOImpl<T extends BaseDTO> implements BaseDAOInt<T> {


	@PersistenceContext
	protected EntityManager entityManager;

	public abstract Class<T> getDTOClass();

	protected abstract List<Predicate> getWhereClause(T dto, CriteriaBuilder builder, Root<T> qRoot);

	protected void populate(T dto, UserContext userContext) {

	}

	
	@Override
	public long add(T dto, UserContext userContext) {

		dto.setCreatedBy(userContext.getLoginId());
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));

		dto.setModifiedBy(userContext.getLoginId());
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

		populate(dto, userContext);

		entityManager.persist(dto);
		return dto.getId();
	}


	@Override
	public void update(T dto, UserContext userContext) {

		dto.setModifiedBy(userContext.getLoginId());
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

		populate(dto, userContext);

		entityManager.merge(dto);
	}

	
	@Override
	public void delete(T dto, UserContext userContext) {

		entityManager.remove(dto);

	}


	@Override
	public T findByPk(long id, UserContext userContext) {
		T dto = entityManager.find(getDTOClass(), id);
		return dto;
	}


	@Override
	public T findByUniqueKey(String attribute, Object value, UserContext userContext) {

		Class<T> dtoClass = getDTOClass();

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> cq = builder.createQuery(dtoClass);

		Root<T> qRoot = cq.from(dtoClass);

		Predicate condition = builder.equal(qRoot.get(attribute), value);

		cq.where(condition);

		TypedQuery<T> query = entityManager.createQuery(cq);

		List<T> list = query.getResultList();

		T dto = null;

		if (list.size() > 0) {
			dto = list.get(0);
		}

		return dto;
	}

	
	protected TypedQuery<T> createCriteria(T dto, UserContext userContext) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> cq = builder.createQuery(getDTOClass());

		Root<T> qRoot = cq.from(getDTOClass());

		cq.select(qRoot);

		List<Predicate> whereClause = getWhereClause(dto, builder, qRoot);

		cq.where(whereClause.toArray(new Predicate[whereClause.size()]));

		TypedQuery<T> query = entityManager.createQuery(cq);

		return query;

	}

	
	@Override
	public List search(T dto, int pageNo, int pageSize, UserContext userContext) {

		TypedQuery<T> query = createCriteria(dto, userContext);

		if (pageSize > 0) {
			query.setFirstResult(pageNo * pageSize);
			query.setMaxResults(pageSize);
		}

		List list = query.getResultList();

		return list;
	}

	
	@Override
	public List search(T dto, UserContext userContext) {

		return search(dto, 0, 0, userContext);
	}


	public List marksheetMeritList(String hql, UserContext userContext) {
		Query q = entityManager.createQuery(hql);
		q.setFirstResult(0);
		q.setMaxResults(10);
		List l = q.getResultList();
		return l;
	}


	protected boolean isEmptyString(String val) {
		return val == null || val.trim().length() == 0;
	}


	protected boolean isZeroNumber(Double val) {
		return val == null || val == 0;
	}

	
	protected boolean isZeroNumber(Long val) {
		return val == null || val == 0;
	}


	protected boolean isZeroNumber(Integer val) {
		return val == null || val == 0;
	}


	protected boolean isNotNull(Object val) {
		return val != null;
	}
}