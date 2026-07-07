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
import com.rays.dto.TimeTableDTO;

/**
 * DAO implementation for {@link TimeTableDTO}.
 * Populates {@code subjectName} and {@code courseName} from their respective DAOs before persist/merge.
 * Supports search filters on subjectName and courseName (prefix-match).
 *
 * @author Ajay Pratap Kerketta
 */
@Repository
public class TimeTableDAOImpl extends BaseDAOImpl<TimeTableDTO> implements TimeTableDAOInt {

	@Autowired
	SubjectDAOInt subjectService;

	@Autowired
	CourseDAOInt courseService;

	/**
	 * @return {@link TimeTableDTO}{@code .class}
	 */
	@Override
	public Class<TimeTableDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return TimeTableDTO.class;
	}

	/**
	 * Populates {@code subjectName} and {@code courseName} by looking up each
	 * entity via its respective ID before persisting or merging.
	 *
	 * @param dto         the timetable DTO to populate
	 * @param userContext the current user's context
	 */
	@Override
	protected void populate(TimeTableDTO dto, UserContext userContext) {
		// TODO Auto-generated method stub
		SubjectDTO subjectDto = subjectService.findByPk(dto.getSubjectId(), userContext);
		if (subjectDto != null) {
			dto.setSubjectName(subjectDto.getName());
		}

		CourseDTO courseDto = courseService.findByPk(dto.getCourseId(), userContext);
		if (courseDto != null) {
			dto.setCourseName(subjectDto.getName());
		}
	}

	/**
	 * Builds WHERE clause predicates for timetable search.
	 * Applies prefix-match (LIKE) on: {@code subjectName}, {@code courseName}
	 * — only for non-empty values.
	 *
	 * @param dto     the filter criteria DTO
	 * @param builder the JPA criteria builder
	 * @param qRoot   the query root for {@link TimeTableDTO}
	 * @return list of predicates to apply; empty list returns all records
	 */
	@Override
	protected List<Predicate> getWhereClause(TimeTableDTO dto, CriteriaBuilder builder, Root<TimeTableDTO> qRoot) {
		// TODO Auto-generated method stub
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getSubjectName())) {

			whereCondition.add(builder.like(qRoot.get("subjectName"), dto.getSubjectName() + "%"));
		}
		if (!isEmptyString(dto.getCourseName())) {

			whereCondition.add(builder.like(qRoot.get("courseName"), dto.getCourseName() + "%"));
		}
		return whereCondition;
	}

}