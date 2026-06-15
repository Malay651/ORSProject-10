package com.rays.common;

import java.util.List;

/**
 * Base DAO interface defining the standard data access contract for all entities.
 * <p>
 * All DAO interfaces in the application should extend this interface and supply
 * the appropriate DTO generic type. Implementations are provided by {@link BaseDAOImpl}.
 * </p>
 *
 * @param <T> the DTO type extending {@link BaseDTO} that this DAO manages
 *
 * @author malay dongre
 */
public interface BaseDAOInt<T extends BaseDTO> {

	public long add(T dto, UserContext userContext);

	public void update(T dto, UserContext userContext);

	public void delete(T dto, UserContext userContext);

	public T findByPk(long id, UserContext userContext);

	public T findByUniqueKey(String attribute, Object value, UserContext userContext);

	public List search(T dto, int pageNo, int pageSize, UserContext userContext);


	public List search(T dto, UserContext userContext);

}