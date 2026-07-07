package com.rays.common;

import com.rays.dto.UserDTO;

/**
 * Holds the context information of the currently authenticated user.
 * <p>
 * An instance of this class is stored in the HTTP session upon login and
 * is passed through the service and DAO layers to provide user identity
 * for audit fields, access control, and business logic.
 * </p>
 *
 * @author Ajay Pratap Kerketta
 */
public class UserContext {

	/** The primary key ID of the logged-in user. Defaults to {@code 0}. */
	private Long userId = 0L;

	/** The login identifier (e.g., email) of the logged-in user. Defaults to {@code "root"}. */
	private String loginId = "root";

	/** The full name of the logged-in user (first name + last name). */
	private String name;

	/** The ID of the role assigned to the logged-in user. Defaults to {@code 0}. */
	Long roleId = 0L;

	/** The name of the role assigned to the logged-in user. Defaults to {@code "root"}. */
	String roleName = "root";

	/** The full {@link UserDTO} object of the logged-in user. */
	private UserDTO userDTO = null;

	/**
	 * Default no-argument constructor.
	 * Creates an empty {@code UserContext} with default values.
	 */
	public UserContext() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor that initializes the context from a {@link UserDTO}.
	 * <p>
	 * Populates {@code userId}, {@code loginId}, {@code name}, {@code roleId},
	 * and {@code roleName} from the provided DTO. The full name is constructed as
	 * {@code firstName + " " + lastName}.
	 * </p>
	 *
	 * @param dto the {@link UserDTO} of the authenticated user; must not be {@code null}
	 */
	public UserContext(UserDTO dto) {
		this.userDTO = dto;
		this.userId = dto.getId();
		this.loginId = dto.getLoginId();
		this.name = dto.getFirstName() + " " + dto.getLastName();
		this.roleId = dto.getRoleId();
		this.roleName = dto.getRoleName();

	}

	/**
	 * Returns the primary key ID of the logged-in user.
	 *
	 * @return the user's {@link Long} ID
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the primary key ID of the logged-in user.
	 *
	 * @param userId the {@link Long} user ID to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Returns the login identifier of the logged-in user.
	 *
	 * @return the login ID as a {@link String} (e.g., an email address)
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * Sets the login identifier of the logged-in user.
	 *
	 * @param loginId the login ID to set (e.g., an email address)
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * Returns the full name of the logged-in user.
	 *
	 * @return the user's full name as a {@link String}
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the full name of the logged-in user.
	 *
	 * @param name the full name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the role ID of the logged-in user.
	 *
	 * @return the {@link Long} role ID
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * Sets the role ID of the logged-in user.
	 *
	 * @param roleId the {@link Long} role ID to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * Returns the role name of the logged-in user.
	 *
	 * @return the role name as a {@link String} (e.g., {@code "ADMIN"}, {@code "USER"})
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * Sets the role name of the logged-in user.
	 *
	 * @param roleName the role name to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * Returns the full {@link UserDTO} of the logged-in user.
	 *
	 * @return the {@link UserDTO} instance, or {@code null} if not set
	 */
	public UserDTO getUserDTO() {
		return userDTO;
	}

	/**
	 * Sets the full {@link UserDTO} of the logged-in user.
	 *
	 * @param userDTO the {@link UserDTO} to associate with this context
	 */
	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	/**
	 * Returns a string representation of this {@code UserContext}, including
	 * userId, loginId, name, roleId, roleName, and the associated UserDTO.
	 *
	 * @return a formatted {@link String} representation of this context
	 */
	@Override
	public String toString() {
		return "UserContext [userId=" + userId + ", loginId=" + loginId + ", name=" + name + ", roleId=" + roleId
				+ ", roleName=" + roleName + ", userDTO=" + userDTO + "]";
	}
	
	
	

}