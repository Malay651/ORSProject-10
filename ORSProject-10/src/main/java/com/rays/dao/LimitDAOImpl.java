package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.dto.LimitDTO;

@Repository
public class LimitDAOImpl extends BaseDAOImpl<LimitDTO> implements LimitDAOInt{

	@Override
	public Class<LimitDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return LimitDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(LimitDTO dto, CriteriaBuilder builder, Root<LimitDTO> qRoot) {
		// TODO Auto-generated method stub
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getLimitCode())) {

			whereCondition.add(builder.like(qRoot.get("limitcode"), dto.getLimitCode() + "%"));
		}

		if (!isEmptyString(dto.getLimitname())) {

			whereCondition.add(builder.like(qRoot.get("limitname"), dto.getLimitname() + "%"));
		}

		if (!isEmptyString(dto.getMaxValue())) {

			whereCondition.add(builder.like(qRoot.get("maxvalue"), dto.getMaxValue() + "%"));
		}
		
		if (!isEmptyString(dto.getStatus())) {

			whereCondition.add(builder.like(qRoot.get("status"), dto.getMaxValue() + "%"));
		}


		return whereCondition;
	}

	}

	


