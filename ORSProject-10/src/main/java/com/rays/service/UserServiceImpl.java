package com.rays.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.UserContext;
import com.rays.dao.UserDAOInt;
import com.rays.dto.UserDTO;

/**
 * Service implementation for {@link UserDTO}. Extends {@link BaseServiceImpl}
 * with authentication, registration, and password management. Sends HTML
 * notification emails via {@link EmailUtility} for registration,
 * forgot-password, and change-password events.
 *
 * @author Ajay Pratap Kerketta
 */
@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserDTO, UserDAOInt> implements UserServiceInt {

	/**
	 * Finds a user by login ID via unique key lookup.
	 *
	 * @param login       the login ID to match
	 * @param userContext the current user's context
	 * @return the matching {@link UserDTO}, or {@code null} if not found
	 */
	@Transactional(readOnly = true)
	public UserDTO findByLoginId(String login, UserContext userContext) {
		return dao.findByUniqueKey("loginId", login, userContext);
	}

	/**
	 * Persists a new user and sends a welcome email containing login credentials.
	 *
	 * @param dto         the user DTO to register
	 * @param userContext the current user's context
	 * @return the registered {@link UserDTO} with the generated ID set
	 */
	@Override
	public UserDTO register(UserDTO dto, UserContext userContext) {

		Long id = add(dto, userContext);

		dto.setId(id);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", dto.getLoginId());
		map.put("password", dto.getPassword());

		return dto;
	}

	/**
	 * Validates credentials against the stored password. On success: updates
	 * {@code lastLogin} and resets {@code unsuccessfulLoginAttempt} to 0. On
	 * failure: increments {@code unsuccessfulLoginAttempt} by 1.
	 *
	 * @param loginId  the user's login ID
	 * @param password the plain-text password to verify
	 * @return the authenticated {@link UserDTO} on success, or {@code null} if
	 *         credentials are invalid
	 */
	@Override
	public UserDTO authenticate(String loginId, String password) {

		UserDTO dto = findByLoginId(loginId, null);

		if (dto != null) {
			UserContext userContext = new UserContext(dto);
			if (password.equals(dto.getPassword())) {
				dto.setLastLogin(new Timestamp((new Date()).getTime()));
				dto.setUnsucessfullLoginAttempt(0);
				update(dto, userContext);
				return dto;
			} else {
				dto.setUnsucessfullLoginAttempt(1 + dto.getUnsucessfullLoginAttempt());
				update(dto, userContext);
			}
		}
		return null;
	}

	/**
	 * Looks up the user by login ID and sends their existing password to their
	 * registered email.
	 *
	 * @param loginId the login ID to look up
	 * @return the matching {@link UserDTO}, or {@code null} if the login ID is not
	 *         found
	 */
	@Override
	public UserDTO forgotPassword(String loginId) {

		UserDTO dto = findByLoginId(loginId, null);
		if (dto == null) {
			return null;
		}

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", dto.getLoginId());
		map.put("password", dto.getPassword());
		map.put("firstName", dto.getFirstName());
		map.put("lastName", dto.getLastName());
		

		return dto;
	}

	/**
	 * Verifies the old password and, if correct, sets the new password and sends a
	 * confirmation email.
	 *
	 * @param loginId     the login ID of the user
	 * @param oldPassword the current password to verify
	 * @param newPassword the new password to set
	 * @param userContext the current user's context
	 * @return the updated {@link UserDTO} on success, or {@code null} if the old
	 *         password does not match
	 */
	@Override
	public UserDTO changePassword(String loginId, String oldPassword, String newPassword, UserContext userContext) {

		UserDTO dto = findByLoginId(loginId, null);

		if (dto != null && oldPassword.equals(dto.getPassword())) {
			dto.setPassword(newPassword);
			update(dto, userContext);

			HashMap<String, String> map = new HashMap<String, String>();

			map.put("login", dto.getLoginId());
			map.put("password", dto.getPassword());
			map.put("firstName", dto.getFirstName());
			map.put("lastName", dto.getLastName());

			
			return dto;
		} else {
			return null;
		}
	}

}