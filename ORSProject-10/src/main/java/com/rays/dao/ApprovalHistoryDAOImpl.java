package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.ApprovalHistoryDTO;

@Repository
public class ApprovalHistoryDAOImpl extends BaseDAOImpl<ApprovalHistoryDTO> implements ApprovalHistoryDAOInt {

	@Override
	public Class<ApprovalHistoryDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return ApprovalHistoryDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(ApprovalHistoryDTO dto, CriteriaBuilder builder,
			Root<ApprovalHistoryDTO> qRoot) {
		// TODO Auto-generated method stub
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getHistoryCode())) {

			whereCondition.add(builder.like(qRoot.get("historycode"), dto.getHistoryCode() + "%"));
		}

		if (!isEmptyString(dto.getApprovedBy())) {

			whereCondition.add(builder.like(qRoot.get("approvedby"), dto.getApprovedBy() + "%"));
		}

		if (isNotNull(dto.getApprovedDate())) {

			whereCondition.add(builder.like(qRoot.get("approveddate"), dto.getApprovedDate() + "%"));
		}

		if (!isEmptyString(dto.getStatus())) {
			
			whereCondition.add(builder.like(qRoot.get("status"), dto.getStatus() + "%"));
		}
		return whereCondition;
	}

	}


