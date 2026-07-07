package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.dto.BookDTO;

@Repository
public class BookDAOImpl extends BaseDAOImpl<BookDTO> implements BookDAOInt {

	@Override
	public Class<BookDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return BookDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(BookDTO dto, CriteriaBuilder builder, Root<BookDTO> qRoot) {
		
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getBookTitle())) {

			whereCondition.add(builder.like(qRoot.get("booktitle"), dto.getBookTitle() + "%"));
		}

		if (!isEmptyString(dto.getBookName())) {

			whereCondition.add(builder.like(qRoot.get("bookname"), dto.getBookName() + "%"));
		}

		if (isNotNull(dto.getBookDate())) {

			whereCondition.add(builder.like(qRoot.get("bookdate"), dto.getBookDate() + "%"));
		}

	

		return whereCondition;
	}



	

}
