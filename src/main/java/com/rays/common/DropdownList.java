package com.rays.common;

/**
 * Interface representing an item in a dropdown list.
 * <p>
 * All entities that can be used to populate dropdown controls in the UI
 * should implement this interface. It provides a consistent contract
 * for retrieving a key (typically the ID) and a display value (typically a name or label).
 * </p>
 *
 * @author Aj
 */
public interface DropdownList {

	/**
	 * Returns the unique key for this dropdown item, typically the entity's primary key.
	 *
	 * @return the key as a {@link String} (e.g., {@code "1"}, {@code "42"})
	 */
	public String getKey();

	/**
	 * Returns the display value for this dropdown item, typically a human-readable name or label.
	 *
	 * @return the display value as a {@link String} (e.g., {@code "Engineering"}, {@code "John Doe"})
	 */
	public String getValue();

}