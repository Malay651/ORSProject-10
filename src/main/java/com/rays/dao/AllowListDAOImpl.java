package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.AllowListDTO;

@Repository
public class AllowListDAOImpl extends BaseDAOImpl<AllowListDTO> implements AllowListDAOInt {

	@Override
	public Class<AllowListDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return AllowListDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(AllowListDTO dto, CriteriaBuilder builder, Root<AllowListDTO> qRoot) {
		// TODO Auto-generated method stub
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getAllowCode())) {

			whereCondition.add(builder.like(qRoot.get("allowcode"), dto.getAllowCode() + "%"));
		}

		if (!isEmptyString(dto.getUsername())) {

			whereCondition.add(builder.like(qRoot.get("username"), dto.getUsername() + "%"));
		}

		if (!isEmptyString(dto.getSource())) {

			whereCondition.add(builder.like(qRoot.get("source"), dto.getSource() + "%"));
		}
       
		if (!isEmptyString(dto.getStatus())) {

			whereCondition.add(builder.like(qRoot.get("status"), dto.getStatus() + "%"));
		}

		
		return whereCondition;
	}


	}


