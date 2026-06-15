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
import com.rays.dto.CollegeDTO;
import com.rays.dto.CourseDTO;
import com.rays.dto.FacultyDTO;
import com.rays.dto.SubjectDTO;

/**
 * DAO implementation for {@link FacultyDTO}.
 * Resolves and populates collegeName, courseName, and subjectName from their respective DAOs.
 * Supports search filters on firstName, email, collegeName, courseName, and subjectName (prefix-match).
 *
 * @author Ajay Pratap Kerketta
 */
@Repository
public class FacultyDAOImpl extends BaseDAOImpl<FacultyDTO> implements FacultyDAOInt {

	@Autowired
	CollegeDAOInt collegeDao;

	@Autowired
	CourseDAOInt courseDao;

	@Autowired
	SubjectDAOInt subjectDao;

	/**
	 * @return {@link FacultyDTO}{@code .class}
	 */
	@Override
	public Class<FacultyDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return FacultyDTO.class;
	}

	/**
	 * Populates {@code collegeName}, {@code courseName}, and {@code subjectName}
	 * by looking up each related entity by its ID before persisting or merging.
	 *
	 * @param dto         the faculty DTO to populate
	 * @param userContext the current user's context
	 */
	@Override
	protected void populate(FacultyDTO dto, UserContext userContext) {
		// TODO Auto-generated method stub
		if (dto.getCollegeId() > 0) {
			CollegeDTO collegeDto = collegeDao.findByPk(dto.getCollegeId(), userContext);
			if (collegeDto != null) {
				dto.setCollegeName(collegeDto.getName());
			}
		}

		if (dto.getCourseId() > 0) {
			CourseDTO courseDto = courseDao.findByPk(dto.getCourseId(), userContext);
			if (courseDto != null) {
				dto.setCourseName(courseDto.getName());
			}
		}

		if (dto.getSubjectId() > 0) {
			SubjectDTO subjectDto = subjectDao.findByPk(dto.getSubjectId(), userContext);
			if (subjectDto != null) {
				dto.setSubjectName(subjectDto.getName());
			}
		}
	}

	/**
	 * Builds WHERE clause predicates for faculty search.
	 * Applies prefix-match (LIKE) on: {@code firstName}, {@code email},
	 * {@code collegeName}, {@code courseName}, {@code subjectName} — only for non-empty values.
	 *
	 * @param dto     the filter criteria DTO
	 * @param builder the JPA criteria builder
	 * @param qRoot   the query root for {@link FacultyDTO}
	 * @return list of predicates to apply; empty list returns all records
	 */
	@Override
	protected List<Predicate> getWhereClause(FacultyDTO dto, CriteriaBuilder builder, Root<FacultyDTO> qRoot) {
		// TODO Auto-generated method stub
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getFirstName())) {

			whereCondition.add(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
		}

		if (!isEmptyString(dto.getEmail())) {

			whereCondition.add(builder.like(qRoot.get("email"), dto.getEmail() + "%"));
		}

		if (!isEmptyString(dto.getCollegeName())) {

			whereCondition.add(builder.like(qRoot.get("collegeName"), dto.getCollegeName() + "%"));
		}

		if (!isEmptyString(dto.getCourseName())) {

			whereCondition.add(builder.like(qRoot.get("courseName"), dto.getCourseName() + "%"));
		}

		if (!isEmptyString(dto.getSubjectName())) {

			whereCondition.add(builder.like(qRoot.get("subjectName"), dto.getSubjectName() + "%"));
		}

		return whereCondition;
	}

}