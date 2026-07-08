package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.OtpDTO;

@Repository
public class OtpDAOImpl extends BaseDAOImpl<OtpDTO> implements OtpDAOInt {

	@Override
	public Class<OtpDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return OtpDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(OtpDTO dto, CriteriaBuilder builder, Root<OtpDTO> qRoot) {
		// TODO Auto-generated method stub
		
      List<Predicate> conditions = new ArrayList<Predicate>();
		
		if (!isEmptyString(dto.getOtpCode())) {
			conditions.add(builder.like(qRoot.get("otpCode"), dto.getOtpCode() + "%"));
		}
		if (!isEmptyString(dto.getMobileNumber())) {
			conditions.add(builder.like(qRoot.get("studentName"), dto.getMobileNumber() + "%"));
		}
		
		if (isNotNull(dto.getExpiryTime())) {
			conditions.add(builder.equal(qRoot.get("dob"), dto.getExpiryTime()));
		}
		if (!isEmptyString(dto.getOtpStatus())) {
			conditions.add(builder.like(qRoot.get("status"), dto.getOtpStatus()));
		}
		
		return conditions;
	
	
	}

}
