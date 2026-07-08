package com.rays.dao;

import java.util.List;

import com.rays.common.BaseDAOInt;
import com.rays.dto.MarksheetDTO;

/**
 * DAO interface for {@link MarksheetDTO}.
 * Extends {@link BaseDAOInt} with an additional merit list query.
 *
 * @author Ajay Pratap Kerketta
 */
public interface MarksheetDAOInt extends BaseDAOInt<MarksheetDTO> {

	/**
	 * Returns the top 10 marksheet records ordered by total marks (physics + chemistry + maths) descending.
	 *
	 * @return list of {@link MarksheetDTO} representing the merit list
	 */
	public List<MarksheetDTO> getMeritList();

}