package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.EnrollmentDTO;
@Repository
public class EnrollmentDAOImpl extends BaseDAOImpl<EnrollmentDTO> implements EnrollmentDAOInt {

	@Override
	public Class<EnrollmentDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return EnrollmentDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(EnrollmentDTO dto, CriteriaBuilder builder, Root<EnrollmentDTO> qRoot) {
		// TODO Auto-generated method stub
     List<Predicate> conditions = new ArrayList<Predicate>();
		
		if (!isEmptyString(dto.getEnrollmentCode())) {
			conditions.add(builder.like(qRoot.get("enrollmentCode"), dto.getEnrollmentCode() + "%"));
		}
		if (!isEmptyString(dto.getStudentName())) {
			conditions.add(builder.like(qRoot.get("studentName"), dto.getStudentName() + "%"));
		}
		
		if (isNotNull(dto.getEnrollmentDate())) {
			conditions.add(builder.equal(qRoot.get("dob"), dto.getEnrollmentDate()));
		}
		if (!isEmptyString(dto.getEnrollmentStatus())) {
			conditions.add(builder.like(qRoot.get("status"), dto.getEnrollmentStatus()));
		}
		
		return conditions;
	
	}

}
