package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.dto.CateringDTO;
import com.rays.dto.DispatchDTO;

@Repository
public class DispatchDAOImpl extends BaseDAOImpl<DispatchDTO> implements DispatchDAOInt {

	@Override
	public Class<DispatchDTO> getDTOClass() {
		// TODO Auto-generated method stub
	   return DispatchDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(DispatchDTO dto, CriteriaBuilder builder, Root<DispatchDTO> qRoot) {
		// TODO Auto-generated method stub
		List<Predicate> whereCondition = new ArrayList<Predicate>();

			if (isNotNull(dto.getDispatchDate())) {

				whereCondition.add(builder.like(qRoot.get("dispatchdate"), dto.getDispatchDate() + "%"));
			}

			if (!isEmptyString(dto.getStatus())) {

				whereCondition.add(builder.like(qRoot.get("status"), dto.getStatus() + "%"));
			}

			if (!isEmptyString(dto.getCourierName())) {

				whereCondition.add(builder.like(qRoot.get("couriername"), dto.getCourierName() + "%"));
			}

			return whereCondition;
		}



	
}
