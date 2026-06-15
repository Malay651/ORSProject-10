package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.common.UserContext;
import com.rays.dto.UserDTO;

/**
 * Service interface for {@link UserDTO}. Extends {@link BaseServiceInt} with
 * authentication, registration, and password management operations.
 *
 * @author Ajay Pratap Kerketta
 */
public interface UserServiceInt extends BaseServiceInt<UserDTO> {

	/**
	 * Finds a user by their login ID.
	 *
	 * @param name        the login ID to search for
	 * @param userContext the current user's context
	 * @return the matching {@link UserDTO}, or {@code null} if not found
	 */
	public UserDTO findByLoginId(String name, UserContext userContext);

	/**
	 * Registers a new user, persists them, and sends a welcome email with
	 * credentials.
	 *
	 * @param dto         the user DTO to register
	 * @param userContext the current user's context
	 * @return the registered {@link UserDTO} with the generated ID set
	 */
	public UserDTO register(UserDTO dto, UserContext userContext);

	/**
	 * Authenticates a user by login ID and password. Updates {@code lastLogin} on
	 * success or increments {@code unsuccessfulLoginAttempt} on failure.
	 *
	 * @param loginId  the user's login ID
	 * @param password the plain-text password to verify
	 * @return the authenticated {@link UserDTO} on success, or {@code null} on
	 *         failure
	 */
	public UserDTO authenticate(String loginId, String password);

	/**
	 * Sends the user's existing password to their registered email if the login ID
	 * is found.
	 *
	 * @param loginId the login ID to look up
	 * @return the matching {@link UserDTO}, or {@code null} if the login ID is not
	 *         found
	 */
	public UserDTO forgotPassword(String loginId);

	/**
	 * Changes a user's password after verifying the old password, then sends a
	 * confirmation email.
	 *
	 * @param loginId     the login ID of the user
	 * @param oldPassword the current password to verify
	 * @param newPassword the new password to set
	 * @param userContext the current user's context
	 * @return the updated {@link UserDTO} on success, or {@code null} if the old
	 *         password does not match
	 */
	public UserDTO changePassword(String loginId, String oldPassword, String newPassword, UserContext userContext);

}