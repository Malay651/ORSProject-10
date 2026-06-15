package com.rays.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.AttachmentDTO;

/**
 * DAO implementation for {@link AttachmentDTO}.
 * No search filters are applied; all records are returned unfiltered.
 *
 * @author Ajay Pratap Kerketta
 */
@Repository
public class AttachmentDAOImpl extends BaseDAOImpl<AttachmentDTO> implements AttachmentDAOInt {

	/**
	 * @return {@link AttachmentDTO}{@code .class}
	 */
	@Override
	public Class<AttachmentDTO> getDTOClass() {
		// TODO Auto-generated method stub
		return AttachmentDTO.class;
	}

	/**
	 * No filters defined for attachments; returns {@code null}.
	 *
	 * @return {@code null}
	 */
	@Override
	protected List<Predicate> getWhereClause(AttachmentDTO dto, CriteriaBuilder builder, Root<AttachmentDTO> qRoot) {
		// TODO Auto-generated method stub
		return null;
	}

}