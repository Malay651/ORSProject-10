package com.rays.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.MarksheetDAOInt;
import com.rays.dto.MarksheetDTO;

/**
 * Service implementation for {@link MarksheetDTO}. Extends
 * {@link BaseServiceImpl} with additional marksheet-specific lookups.
 *
 * @author Ajay Pratap Kerketta
 */
@Service
@Transactional
public class MarksheetServiceImpl extends BaseServiceImpl<MarksheetDTO, MarksheetDAOInt>
		implements MarksheetServiceInt {

	/**
	 * Finds a marksheet by student name via unique key lookup.
	 *
	 * @param name        the student name to match
	 * @param userContext the current user's context
	 * @return the matching {@link MarksheetDTO}, or {@code null} if not found
	 */
	@Override
	public MarksheetDTO findByName(String name, UserContext userContext) {
		// TODO Auto-generated method stub
		return dao.findByUniqueKey("name", name, userContext);
	}

	/**
	 * Finds a marksheet by roll number via unique key lookup.
	 *
	 * @param rollNo      the roll number to match
	 * @param userContext the current user's context
	 * @return the matching {@link MarksheetDTO}, or {@code null} if not found
	 */
	@Override
	public MarksheetDTO findByRollNo(String rollNo, UserContext userContext) {
		// TODO Auto-generated method stub
		return dao.findByUniqueKey("rollNo", rollNo, userContext);
	}

	/**
	 * Returns the top 10 marksheets ordered by total marks descending.
	 *
	 * @param userContext the current user's context (not used by the DAO query)
	 * @return list of {@link MarksheetDTO} representing the merit list
	 */
	@Override
	public List<MarksheetDTO> getMeritList(UserContext userContext) {
		// TODO Auto-generated method stub
		return dao.getMeritList();
	}

}