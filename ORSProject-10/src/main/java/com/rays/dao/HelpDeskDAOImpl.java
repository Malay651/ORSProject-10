package com.rays.dao;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.BaseDAOInt;
import com.rays.common.BaseDTO;
import com.rays.common.BaseServiceImpl;
import com.rays.dto.HelpDeskDTO;
@Repository
public class HelpDeskDAOImpl extends BaseDAOImpl<HelpDeskDTO> implements HelpDeskDAOInt {

	@Override
	public Class<HelpDeskDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return HelpDeskDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(HelpDeskDTO dto, CriteriaBuilder builder, Root<HelpDeskDTO> qRoot) {
		// TODO Auto-generated method stub
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getHelpDeskCode())) {

			whereCondition.add(builder.like(qRoot.get("helpdeskcode"), dto.getHelpDeskCode() + "%"));
		}

		if (!isEmptyString(dto.getUserName())) {

			whereCondition.add(builder.like(qRoot.get("username"), dto.getUserName() + "%"));
		}

		if (!isEmptyString(dto.getQuery())) {

			whereCondition.add(builder.equal(qRoot.get("query"), dto.getQuery()));
		}
       
		if (!isEmptyString(dto.getStatus())) {
			
			whereCondition.add(builder.equal(qRoot.get("status"), dto.getStatus()));
			
		}
		return whereCondition;

	}

}
