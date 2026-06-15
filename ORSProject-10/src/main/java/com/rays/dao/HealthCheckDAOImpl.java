package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.HealthCheckDTO;

@Repository
public class HealthCheckDAOImpl extends BaseDAOImpl<HealthCheckDTO> implements HealthCheckDAOInt {

	@Override
	public Class<HealthCheckDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return HealthCheckDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(HealthCheckDTO dto, CriteriaBuilder builder, Root<HealthCheckDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getHealthCode())) {

			whereCondition.add(builder.like(qRoot.get("helpdeskcode"), dto.getHealthCode() + "%"));
		}

		if (!isEmptyString(dto.getServicename())) {

			whereCondition.add(builder.like(qRoot.get("username"), dto.getServicename() + "%"));
		}

		if (!isEmptyString(dto.getUptime())) {

			whereCondition.add(builder.equal(qRoot.get("query"), dto.getUptime()));
		}
       
		if (!isEmptyString(dto.getStatus())) {
			
			whereCondition.add(builder.equal(qRoot.get("status"), dto.getStatus()));
			
		}
		return whereCondition;


	}

}
