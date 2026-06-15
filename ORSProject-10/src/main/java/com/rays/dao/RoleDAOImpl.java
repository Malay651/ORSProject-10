package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.RoleDTO;

/**
 * DAO implementation for {@link RoleDTO}.
 * Supports search filters on id (exact-match) and name (prefix-match).
 *
 * @author Ajay Pratap Kerketta
 */
@Repository
public class RoleDAOImpl extends BaseDAOImpl<RoleDTO> implements RoleDAOInt {

	/**
	 * @return {@link RoleDTO}{@code .class}
	 */
	@Override
	public Class<RoleDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return RoleDTO.class;
	}

	/**
	 * Builds WHERE clause predicates for role search.
	 * Applies exact-match on {@code id} and prefix-match (LIKE) on {@code name}
	 * — only for non-zero/non-empty values.
	 *
	 * @param dto     the filter criteria DTO
	 * @param builder the JPA criteria builder
	 * @param qRoot   the query root for {@link RoleDTO}
	 * @return list of predicates to apply; empty list returns all records
	 */
	@Override
	protected List<Predicate> getWhereClause(RoleDTO dto, CriteriaBuilder builder, Root<RoleDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isZeroNumber(dto.getId())) {
			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		if (!isEmptyString(dto.getName())) {
			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		return whereCondition;
	}

}