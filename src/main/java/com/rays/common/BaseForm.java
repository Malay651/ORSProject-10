package com.rays.common;

/**
 * Base class for all form beans used in REST request body binding.
 * <p>
 * Holds common fields such as primary key, audit information, pagination parameters,
 * and the operation type. Concrete form classes should extend this and override
 * {@link #getDto()} to return a populated {@link BaseDTO} instance.
 * </p>
 *
 * @author Ajay Pratap Kerketta
 */
public class BaseForm {

	/** The primary key of the entity, used to distinguish add vs update operations. */
	protected Long id;

	/** The login ID of the user who created the record (used in search/display). */
	protected String createdBy;

	/** The login ID of the user who last modified the record (used in search/display). */
	protected String modifiedBy;

	/** The creation timestamp in epoch milliseconds (used in search/display). */
	protected long createdDatetime;

	/** The last modification timestamp in epoch milliseconds (used in search/display). */
	protected long modifiedDatetime;

	/** An array of IDs used for bulk operations such as delete-many. */
	private Long[] ids;

	/** The zero-based page index for paginated search results. Defaults to {@code 0}. */
	private int pageNo = 0;

	/** The number of records per page for paginated search results. Defaults to {@code 5}. */
	private int pageSize = 5;

	/** A string identifier for the operation type (e.g., {@code "add"}, {@code "update"}). */
	private String operation;

	/**
	 * Returns the primary key of the entity.
	 *
	 * @return the {@link Long} ID, or {@code null} if not set
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the primary key of the entity.
	 *
	 * @param id the {@link Long} primary key to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Converts this form into its corresponding {@link BaseDTO}.
	 * <p>
	 * Default implementation returns {@code null}. Subclasses must override this
	 * method to construct and return a properly populated DTO.
	 * </p>
	 *
	 * @return a {@link BaseDTO} instance populated with this form's data, or {@code null} in the base implementation
	 */
	public BaseDTO getDto() {
		return null;
	}

	/**
	 * Initializes a given DTO with the ID from this form.
	 * <p>
	 * If the form's ID is not null and greater than 0, it is set on the DTO (update case).
	 * Otherwise, the DTO's ID is set to {@code null} (add case).
	 * </p>
	 *
	 * @param <T> the DTO type extending {@link BaseDTO}
	 * @param dto the DTO instance to initialize with the form's ID
	 * @return the same {@code dto} instance with its ID set appropriately
	 */
	public <T extends BaseDTO> T initDTO(T dto) {

		if (id != null && id > 0) {
			dto.setId(id);
		} else {
			dto.setId(null);
		}
		return dto;
	}

}