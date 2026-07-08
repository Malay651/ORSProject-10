package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.dto.CourseDTO;
import com.rays.dto.SubjectDTO;

/**
 * DAO implementation for {@link SubjectDTO}.
 * Populates {@code courseName} from {@link CourseDAOInt} before persist/merge.
 * Supports search filters on name and courseName (prefix-match).
 *
 * @author Ajay Pratap Kerketta
 */
@Repository
public class SubjectDAOImpl extends BaseDAOImpl<SubjectDTO> implements SubjectDAOInt {

	@Autowired
	CourseDAOInt courseDao;

	/**
	 * @return {@link SubjectDTO}{@code .class}
	 */
	@Override
	public Class<SubjectDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return SubjectDTO.class;
	}

	/**
	 * Populates {@code courseName} by looking up the course via {@code courseId}
	 * when {@code courseId} is non-zero.
	 *
	 * @param dto         the subject DTO to populate
	 * @param userContext the current user's context
	 */
	@Override
	protected void populate(SubjectDTO dto, UserContext userContext) {
		// TODO Auto-generated method stub
		if (dto.getCourseId() != 0) {

			CourseDTO courseDto = courseDao.findByPk(dto.getCourseId(), userContext);

			if (courseDto != null) {
				dto.setCourseName(courseDto.getName());
			}
		}
	}

	/**
	 * Builds WHERE clause predicates for subject search.
	 * Applies prefix-match (LIKE) on: {@code name}, {@code courseName}
	 * — only for non-empty values.
	 *
	 * @param dto     the filter criteria DTO
	 * @param builder the JPA criteria builder
	 * @param qRoot   the query root for {@link SubjectDTO}
	 * @return list of predicates to apply; empty list returns all records
	 */
	@Override
	protected List<Predicate> getWhereClause(SubjectDTO dto, CriteriaBuilder builder, Root<SubjectDTO> qRoot) {
		// TODO Auto-generated method stub
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		if (!isEmptyString(dto.getCourseName())) {

			whereCondition.add(builder.like(qRoot.get("courseName"), dto.getCourseName() + "%"));
		}

		return whereCondition;
	}

}