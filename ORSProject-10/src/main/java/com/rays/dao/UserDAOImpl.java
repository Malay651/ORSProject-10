package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;

/**
 * DAO implementation for {@link UserDTO}.
 * Resolves {@code roleName} from {@link RoleDAOInt} and preserves {@code lastLogin}
 * from the existing record before persist/merge.
 * Supports search filters on firstName, loginId, roleId, dob, and status.
 *
 * @author Ajay Pratap Kerketta
 */
@Repository
public class UserDAOImpl extends BaseDAOImpl<UserDTO> implements UserDAOInt {

	@Autowired
	RoleDAOInt roledao;

	/**
	 * @return {@link UserDTO}{@code .class}
	 */
	@Override
	public Class<UserDTO> getDTOClass() {

		return UserDTO.class;
	}

	/**
	 * Populates {@code roleName} by looking up the role via {@code roleId},
	 * and preserves {@code lastLogin} from the existing persisted record when updating.
	 *
	 * @param dto         the user DTO to populate
	 * @param userContext the current user's context
	 */
	@Override
	protected void populate(UserDTO dto, UserContext userContext) {
		// TODO Auto-generated method stub
		if (dto.getRoleId() != null && dto.getRoleId() > 0) {
			RoleDTO roleDto = roledao.findByPk(dto.getRoleId(), userContext);
			dto.setRoleName(roleDto.getName());
		}

		if (dto.getId() != null && dto.getId() > 0) {
			UserDTO userDto = findByPk(dto.getId(), userContext);
			dto.setLastLogin(userDto.getLastLogin());
		}
	}

	/**
	 * Builds WHERE clause predicates for user search.
	 * Applies prefix-match (LIKE) on: {@code firstName}, {@code loginId};
	 * exact-match on: {@code roleId}, {@code dob}, {@code status}
	 * — only for non-empty/non-zero/non-null values.
	 *
	 * @param dto     the filter criteria DTO
	 * @param builder the JPA criteria builder
	 * @param qRoot   the query root for {@link UserDTO}
	 * @return list of predicates to apply; empty list returns all records
	 */
	@Override
	protected List<Predicate> getWhereClause(UserDTO dto, CriteriaBuilder builder, Root<UserDTO> qRoot) {
		// TODO Auto-generated method stub
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getFirstName())) {

			whereCondition.add(builder.like(qRoot.get("firstName"), dto.getFirstName() + "%"));
		}
		if (!isEmptyString(dto.getLoginId())) {

			whereCondition.add(builder.like(qRoot.get("loginId"), dto.getLoginId() + "%"));
		}
		if (!isZeroNumber(dto.getRoleId())) {

			whereCondition.add(builder.equal(qRoot.get("roleId"), dto.getRoleId()));
		}
		if (isNotNull(dto.getDob())) {

			whereCondition.add(builder.equal(qRoot.get("dob"), dto.getDob()));
		}
		if (!isEmptyString(dto.getStatus())) {

			whereCondition.add(builder.equal(qRoot.get("status"), dto.getStatus()));
		}
		return whereCondition;
	}

}