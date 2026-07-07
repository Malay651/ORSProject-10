package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.CateringDTO;

@Repository
public class CateringDAOImpl extends BaseDAOImpl<CateringDTO> implements CateringDAOInt{

	@Override
	public Class<CateringDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return CateringDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(CateringDTO dto, CriteriaBuilder builder, Root<CateringDTO> qRoot) {
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getVendorName())) {

			whereCondition.add(builder.like(qRoot.get("vendorname"), dto.getVendorName() + "%"));
		}

		if (!isEmptyString(dto.getMenuType())) {

			whereCondition.add(builder.like(qRoot.get("menutype"), dto.getMenuType() + "%"));
		}

		if (isNotNull(dto.getCost())) {

			whereCondition.add(builder.like(qRoot.get("cost"), dto.getCost() + "%"));
		}

		return whereCondition;
	}

	}


