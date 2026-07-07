package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dto.HelpDeskDTO;
import com.rays.dto.SecurityAlertDTO;
import com.rays.service.SecurityAlertServiceInt;

@Repository
public class SecurityAlertDAOImpl extends BaseDAOImpl<SecurityAlertDTO> implements SecurityAlertDAOInt{

	@Override
	public Class<SecurityAlertDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return SecurityAlertDTO.class;
	}



	@Override
	protected List<Predicate> getWhereClause(SecurityAlertDTO dto, CriteriaBuilder builder,
			Root<SecurityAlertDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();

	    

	    if (!isEmptyString(dto.getThreatLevel())) {

	        whereCondition.add(
	            builder.like(qRoot.get("threatLevel"),
	            dto.getThreatLevel() + "%"));
	    }

	    if (!isEmptyString(dto.getSourceIp())) {

	        whereCondition.add(
	            builder.like(qRoot.get("sourceIp"),
	            dto.getSourceIp() + "%"));
	    }

	    if (dto.getDetectedTime() != null) {

	        whereCondition.add(
	            builder.equal(qRoot.get("detectedTime"),
	            dto.getDetectedTime()));
	    }

	    if (!isEmptyString(dto.getStatus())) {

	        whereCondition.add(
	            builder.equal(qRoot.get("status"),
	            dto.getStatus()));
	    }

	    return whereCondition;

	}
	}

	
	


