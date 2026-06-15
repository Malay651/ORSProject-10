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
import com.rays.dto.StudentDTO;

/**
 * DAO implementation for {@link StudentDTO}.
 * Populates {@code collegeName} from {@link CollegeDAOInt} before persist/merge.
 * Supports search filters on enrolNo, firstName, collegeName, email, dob, and phoneNo.
 *
 * @author Ajay Pratap Kerketta
 */
@Repository
public class StudentDAOImpl extends BaseDAOImpl<StudentDTO> implements StudentDAOInt {

	@Autowired
	CollegeDAOInt collegedao = null;

	/**
	 * @return {@link StudentDTO}{@code .class}
	 */
	@Override
	public Class<StudentDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return StudentDTO.class;
	}

	/**
	 * Populates {@code collegeName} by looking up the college via {@code collegeId}.
	 *
	 * @param dto         the student DTO to populate
	 * @param userContext the current user's context
	 */
	@Override
	protected void populate(StudentDTO dto, UserContext userContext) {
		// TODO Auto-generated method stub
		CollegeDTO collegeDto = collegedao.findByPk(dto.getCollegeId(), userContext);
		if (collegeDto != null) {
			dto.setCollegeName(collegeDto.getName());
		}
	}

	/**
	 * Builds WHERE clause predicates for student search.
	 * Applies prefix-match (LIKE) on: {@code enrolNo}, {@code firstName}, {@code collegeName},
	 * {@code email}, {@code phoneNo}; exact-match on {@code dob} — only for non-empty/non-null values.
	 *
	 * @param dto     the filter criteria DTO
	 * @param builder the JPA criteria builder
	 * @param qRoot   the query root for {@link StudentDTO}
	 * @return list of predicates to apply; empty list returns all records
	 */
	@Override
	protected List<Predicate> getWhereClause(StudentDTO dto, CriteriaBuilder builder, Root<StudentDTO> qRoot) {
		// TODO Auto-generated method stub
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getEnrolNo())) {

			whereCondition.add(builder.like(qRoot.get("enrolNo"), dto.getEnrolNo() + "%"));
		}

		if (!isEmptyString(dto.getFirstName())) {

			whereCondition.add(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
		}

		if (!isEmptyString(dto.getCollegeName())) {

			whereCondition.add(builder.like(qRoot.get("collegeName"), dto.getCollegeName() + "%"));
		}

		if (!isEmptyString(dto.getEmail())) {

			whereCondition.add(builder.like(qRoot.get("email"), dto.getEmail() + "%"));
		}

		if (isNotNull(dto.getDob())) {

			whereCondition.add(builder.equal(qRoot.get("dob"), dto.getDob()));
		}

		if (!isEmptyString(dto.getPhoneNo())) {
			whereCondition.add(builder.like(qRoot.get("phoneNo"), dto.getPhoneNo() + "%"));
		}

		return whereCondition;
	}

}