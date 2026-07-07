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
import com.rays.dto.MarksheetDTO;
import com.rays.dto.StudentDTO;

/**
 * DAO implementation for {@link MarksheetDTO}.
 * Populates the student's full name from {@link StudentDAOInt} before persist/merge.
 * Supports search filters on name, rollNo, and studentId.
 *
 * @author Ajay Pratap Kerketta
 */
@Repository
public class MarksheetDAOImpl extends BaseDAOImpl<MarksheetDTO> implements MarksheetDAOInt {

	@Autowired
	StudentDAOInt studentDao = null;

	/**
	 * @return {@link MarksheetDTO}{@code .class}
	 */
	@Override
	public Class<MarksheetDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return MarksheetDTO.class;
	}

	/**
	 * Populates the marksheet's {@code name} field by looking up the student's
	 * first and last name via {@code studentId}.
	 *
	 * @param dto         the marksheet DTO to populate
	 * @param userContext the current user's context
	 */
	@Override
	protected void populate(MarksheetDTO dto, UserContext userContext) {
		// TODO Auto-generated method stub

		if (dto.getStudentId() != null) {
			StudentDTO studentDto = studentDao.findByPk(dto.getStudentId(), userContext);
			if (studentDto != null) {
				dto.setName(studentDto.getFirstName() + " " + studentDto.getLastName());
			}
		}
	}

	/**
	 * Builds WHERE clause predicates for marksheet search.
	 * Applies prefix-match on {@code name} and {@code rollNo};
	 * exact-match on {@code studentId} — only for non-empty/non-zero values.
	 *
	 * @param dto     the filter criteria DTO
	 * @param builder the JPA criteria builder
	 * @param qRoot   the query root for {@link MarksheetDTO}
	 * @return list of predicates to apply; empty list returns all records
	 */
	@Override
	protected List<Predicate> getWhereClause(MarksheetDTO dto, CriteriaBuilder builder, Root<MarksheetDTO> qRoot) {
		// TODO Auto-generated method stub
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		if (!isEmptyString(dto.getRollNo())) {

			whereCondition.add(builder.like(qRoot.get("rollNo"), dto.getRollNo() + "%"));
		}

		if (!isZeroNumber(dto.getStudentId())) {

			whereCondition.add(builder.equal(qRoot.get("studentId"), dto.getStudentId()));
		}

		return whereCondition;
	}

	/**
	 * Returns the top 10 marksheets ordered by total marks (physics + chemistry + maths) descending.
	 *
	 * @return list of {@link MarksheetDTO} representing the merit list
	 */
	@Override
	public List<MarksheetDTO> getMeritList() {
		List list = super.marksheetMeritList("from MarksheetDTO order by (physics+chemistry+maths) desc", null);
		return list;
	}

}