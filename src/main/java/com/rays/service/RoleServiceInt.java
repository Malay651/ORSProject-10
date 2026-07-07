package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.RoleDTO;

/**
 * Service interface for {@link RoleDTO}.
 * Extends {@link BaseServiceInt} with an additional name-based lookup.
 *
 * @author Ajay Pratap Kerketta
 */
public interface RoleServiceInt extends BaseServiceInt<RoleDTO> {

	/**
	 * Finds a role by its name.
	 *
	 * @param name        the role name to search for
	 * @param userContext the current user's context
	 * @return the matching {@link RoleDTO}, or {@code null} if not found
	 */
	public RoleDTO findByName(String name, UserContext userContext);

}