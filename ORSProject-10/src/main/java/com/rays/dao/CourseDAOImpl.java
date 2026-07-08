package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.CourseDTO;

/**
 * DAO implementation for {@link CourseDTO}.
 * Supports search filters on name, description, and duration (prefix-match).
 *
 * @author Ajay Pratap Kerketta
 */
@Repository
public class CourseDAOImpl extends BaseDAOImpl<CourseDTO> implements CourseDAOInt {

	/**
	 * @return {@link CourseDTO}{@code .class}
	 */
	@Override
	public Class<CourseDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return CourseDTO.class;
	}

	/**
	 * Builds WHERE clause predicates for course search.
	 * Applies prefix-match (LIKE) on: {@code name}, {@code description}, {@code duration}
	 * — only for non-empty values.
	 *
	 * @param dto     the filter criteria DTO
	 * @param builder the JPA criteria builder
	 * @param qRoot   the query root for {@link CourseDTO}
	 * @return list of predicates to apply; empty list returns all records
	 */
	@Override
	protected List<Predicate> getWhereClause(CourseDTO dto, CriteriaBuilder builder, Root<CourseDTO> qRoot) {
		// TODO Auto-generated method stub
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		if (!isEmptyString(dto.getDescription())) {

			whereCondition.add(builder.like(qRoot.get("description"), dto.getDescription() + "%"));
		}

		if (!isEmptyString(dto.getDuration())) {

			whereCondition.add(builder.like(qRoot.get("duration"), dto.getDuration() + "%"));
		}

		return whereCondition;
	}

}