package com.rays.common;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * Abstract base class for all Data Transfer Objects (DTOs) in the application.
 * <p>
 * Provides common persistence fields including primary key, audit columns
 * (created/modified by and datetime), and declares abstract methods that
 * each concrete DTO must implement for unique-key validation and labeling.
 * Also implements the {@link DropdownList} interface to support use in dropdown controls.
 * </p>
 *
 * @author malay dongre
 */
@MappedSuperclass
public abstract class BaseDTO implements DropdownList {

	/**
	 * The auto-generated primary key for the entity.
	 * Uses a native generation strategy via Hibernate's {@code GenericGenerator}.
	 */
	@Id
	@GeneratedValue(generator = "pk")
	@GenericGenerator(name = "pk", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false)
	protected Long id;

	/** The login ID of the user who created this record. */
	@Column(name = "CREATED_BY", length = 50)
	protected String createdBy;

	/** The login ID of the user who last modified this record. */
	@Column(name = "MODIFIED_BY", length = 50)
	protected String modifiedBy;

	/** The timestamp when this record was first created. */
	@Column(name = "CREATED_DATETIME")
	protected Timestamp createdDatetime;

	/** The timestamp when this record was last modified. */
	@Column(name = "MODIFIED_DATETIME")
	protected Timestamp modifiedDatetime;

	/**
	 * Returns the primary key of the entity.
	 *
	 * @return the {@link Long} ID, or {@code null} if not yet persisted
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the primary key of the entity.
	 *
	 * @param id the {@link Long} primary key value to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Returns the login ID of the user who created this record.
	 *
	 * @return the creator's login ID as a {@link String}
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the login ID of the user who created this record.
	 *
	 * @param createdBy the creator's login ID
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Returns the login ID of the user who last modified this record.
	 *
	 * @return the modifier's login ID as a {@link String}
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Sets the login ID of the user who last modified this record.
	 *
	 * @param modifiedBy the modifier's login ID
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Returns the timestamp when this record was created.
	 *
	 * @return the creation {@link Timestamp}
	 */
	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}

	/**
	 * Sets the timestamp when this record was created.
	 *
	 * @param createdDatetime the creation {@link Timestamp} to set
	 */
	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	/**
	 * Returns the timestamp when this record was last modified.
	 *
	 * @return the last modification {@link Timestamp}
	 */
	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	/**
	 * Sets the timestamp when this record was last modified.
	 *
	 * @param modifiedDatetime the modification {@link Timestamp} to set
	 */
	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	/**
	 * Returns the field name used as the unique key for duplicate-check validation.
	 * <p>
	 * For example, a {@code CollegeDTO} might return {@code "name"} to prevent
	 * duplicate college names.
	 * </p>
	 *
	 * @return the entity attribute name used as the unique key, or {@code null} if no unique check is needed
	 */
	public abstract String getUniqueKey();

	/**
	 * Returns the value of the unique key field for duplicate-check validation.
	 *
	 * @return the string value of the unique field to check against existing records
	 */
	public abstract String getUniqueValue();

	/**
	 * Returns a human-readable label for the entity, used in messages such as
	 * "College already exists".
	 *
	 * @return the display label for this entity type as a {@link String}
	 */
	public abstract String getLabel();

	/**
	 * Returns the name of the database table that this entity maps to.
	 *
	 * @return the table name as a {@link String}
	 */
	public abstract String getTableName();

	/**
	 * Returns the string representation of the primary key, as required by the
	 * {@link DropdownList} interface.
	 *
	 * @return the primary key as a {@link String}
	 */
	@Override
	public String getKey() {

		return String.valueOf(id);
	}

}