package com.rays.common;

import java.util.List;

/**
 * Base service interface defining the standard business logic contract for all entities.
 * <p>
 * All service interfaces in the application should extend this interface and supply
 * the appropriate DTO generic type. Implementations are provided by {@link BaseServiceImpl}.
 * </p>
 *
 * @param <T> the DTO type extending {@link BaseDTO} that this service manages
 *
 * @author Ajay Pratap Kerketta
 */
public interface BaseServiceInt<T extends BaseDTO> {

	/**
	 * Persists a new DTO entity to the database.
	 *
	 * @param dto         the DTO entity to add
	 * @param userContext the current user's context used for audit fields
	 * @return the generated primary key ({@code id}) of the newly added entity
	 */
	public long add(T dto, UserContext userContext);

	/**
	 * Updates an existing DTO entity in the database.
	 *
	 * @param dto         the DTO entity with updated values; must have a valid ID
	 * @param userContext the current user's context used for audit fields
	 */
	public void update(T dto, UserContext userContext);

	/**
	 * Saves a DTO entity — adds it if new, updates it if existing.
	 *
	 * @param dto         the DTO entity to save
	 * @param userContext the current user's context
	 * @return the primary key of the saved entity
	 */
	public long save(T dto, UserContext userContext);

	/**
	 * Deletes an entity from the database by its primary key.
	 *
	 * @param id          the primary key of the entity to delete
	 * @param userContext the current user's context
	 * @return the deleted DTO entity
	 * @throws RuntimeException if no entity with the given ID exists
	 */
	public T delete(long id, UserContext userContext);

	/**
	 * Finds and returns a single entity by its primary key.
	 *
	 * @param id          the primary key of the entity to retrieve
	 * @param userContext the current user's context
	 * @return the matching DTO entity, or {@code null} if not found
	 */
	public T findById(long id, UserContext userContext);

	/**
	 * Finds and returns a single entity matching a given attribute-value pair.
	 *
	 * @param attribute   the entity field name to match against (e.g., {@code "email"})
	 * @param val         the string value the specified attribute must equal
	 * @param userContext the current user's context
	 * @return the first matching DTO entity, or {@code null} if no match is found
	 */
	public T findByUniqueKey(String attribute, String val, UserContext userContext);

	/**
	 * Searches for entities matching the filter criteria in the given DTO, with pagination.
	 *
	 * @param dto         the DTO containing search filter criteria
	 * @param pageNo      the zero-based page index
	 * @param pageSize    the maximum number of records per page
	 * @param userContext the current user's context
	 * @return a {@link List} of matching DTO entities for the specified page
	 */
	public List search(T dto, int pageNo, int pageSize, UserContext userContext);

	/**
	 * Searches for all entities matching the filter criteria in the given DTO, without pagination.
	 *
	 * @param dto         the DTO containing search filter criteria
	 * @param userContext the current user's context
	 * @return a {@link List} of all matching DTO entities
	 */
	public List search(T dto, UserContext userContext);

}