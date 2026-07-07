package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.LibrarySystemDTO;

@Repository
public class LibrarySystemDAOImpl extends BaseDAOImpl<LibrarySystemDTO> implements LibrarySystemDAOInt{

	@Override
	public Class<LibrarySystemDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return LibrarySystemDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(LibrarySystemDTO dto, CriteriaBuilder builder,
			Root<LibrarySystemDTO> qRoot) {
		
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getBookName())) {

			whereCondition.add(builder.like(qRoot.get("bookname"), dto.getBookName() + "%"));
		}

		if (!isEmptyString(dto.getAuthorName())) {

			whereCondition.add(builder.like(qRoot.get("authorname"), dto.getAuthorName() + "%"));
		}

		if (!isNotNull(dto.getIssueDate())) {

			whereCondition.add(builder.like(qRoot.get("issuedate"), dto.getIssueDate() + "%"));
		}
       
		if (!isZeroNumber(dto.getPrice())) {

			whereCondition.add(builder.like(qRoot.get("price"), dto.getPrice() + "%"));
		}

		
		return whereCondition;
	}



	}


