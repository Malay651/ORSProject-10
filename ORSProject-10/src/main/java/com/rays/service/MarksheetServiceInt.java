package com.rays.service;

import java.util.List;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.MarksheetDTO;

/**
 * Service interface for {@link MarksheetDTO}. Extends {@link BaseServiceInt}
 * with additional marksheet-specific lookup operations.
 *
 * @author Ajay Pratap Kerketta
 */
public interface MarksheetServiceInt extends BaseServiceInt<MarksheetDTO> {

	/**
	 * Finds a marksheet by student name.
	 *
	 * @param name        the student name to search for
	 * @param userContext the current user's context
	 * @return the matching {@link MarksheetDTO}, or {@code null} if not found
	 */
	public MarksheetDTO findByName(String name, UserContext userContext);

	/**
	 * Finds a marksheet by roll number.
	 *
	 * @param rollNo      the roll number to search for
	 * @param userContext the current user's context
	 * @return the matching {@link MarksheetDTO}, or {@code null} if not found
	 */
	public MarksheetDTO findByRollNo(String rollNo, UserContext userContext);

	/**
	 * Returns the top 10 marksheets ordered by total marks (physics + chemistry +
	 * maths) descending.
	 *
	 * @param userContext the current user's context
	 * @return list of {@link MarksheetDTO} representing the merit list
	 */
	public List<MarksheetDTO> getMeritList(UserContext userContext);

}