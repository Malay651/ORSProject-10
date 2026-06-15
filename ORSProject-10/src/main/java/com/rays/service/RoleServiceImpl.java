package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.RoleDAOInt;
import com.rays.dto.RoleDTO;

/**
 * Service implementation for {@link RoleDTO}. Extends {@link BaseServiceImpl}
 * with an additional name-based lookup.
 *
 * @author Ajay Pratap Kerketta
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<RoleDTO, RoleDAOInt> implements RoleServiceInt {

	/**
	 * Finds a role by its name via unique key lookup.
	 *
	 * @param name        the role name to match
	 * @param userContext the current user's context
	 * @return the matching {@link RoleDTO}, or {@code null} if not found
	 */
	@Transactional(readOnly = true)
	public RoleDTO findByName(String name, UserContext userContext) {
		return dao.findByUniqueKey("name", name, userContext);
	}

}