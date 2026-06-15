package com.rays.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Standard response wrapper for all REST API responses in the application.
 * <p>
 * Encapsulates a {@code success} flag and a flexible result map that can hold
 * messages, data objects, input validation errors, and any other key-value pairs.
 * All controller methods return this class to provide a consistent response structure
 * to the frontend.
 * </p>
 *
 * @author malay dongre
 */
public class ORSResponse {

	/** Key used to store field-level input validation errors in the result map. */
	public static final String INPUT_ERROR = "inputerror";

	/** Key used to store a general message (success or error) in the result map. */
	public static final String MESSAGE = "message";

	/** Key used to store the primary response data (DTO or list) in the result map. */
	public static final String DATA = "data";

	/**
	 * A flexible map holding all response data including messages, data objects,
	 * input errors, and any additional metadata.
	 */
	private Map<String, Object> result = new HashMap<String, Object>();

	/**
	 * Indicates whether the operation was successful.
	 * {@code true} for success, {@code false} for failure.
	 */
	public boolean success = false;

	/**
	 * Default constructor. Creates a response with {@code success=false}.
	 */
	public ORSResponse() {

	}

	/**
	 * Constructor that sets the success flag explicitly.
	 *
	 * @param success {@code true} to mark the response as successful; {@code false} otherwise
	 */
	public ORSResponse(boolean success) {

		this.success = success;
	}

	/**
	 * Returns the result map containing all response data.
	 *
	 * @return a {@link Map} of key-value pairs representing the response body content
	 */
	public Map<String, Object> getResult() {
		return result;
	}

	/**
	 * Replaces the entire result map with the given map.
	 *
	 * @param result the {@link Map} to set as the new result map
	 */
	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	/**
	 * Returns whether the operation was successful.
	 *
	 * @return {@code true} if the operation succeeded; {@code false} otherwise
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Sets the success flag on this response.
	 *
	 * @param success {@code true} to mark as successful; {@code false} to mark as failed
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Adds field-level input validation errors to the result map under the key {@code "inputerror"}.
	 *
	 * @param value the validation errors object; typically a {@link java.util.Map} of field name to error message
	 */
	public void addInputError(Object value) {
		result.put(INPUT_ERROR, value);
	}

	/**
	 * Adds a general message (success confirmation or error description) to the result map
	 * under the key {@code "message"}.
	 *
	 * @param value the message object; typically a {@link String}
	 */
	public void addMessage(Object value) {
		result.put(MESSAGE, value);
	}

	/**
	 * Adds the primary response data (such as a DTO or list of DTOs) to the result map
	 * under the key {@code "data"}.
	 *
	 * @param value the data object to include in the response; typically a DTO or {@link java.util.List}
	 */
	public void addData(Object value) {
		result.put(DATA, value);
	}

	/**
	 * Adds a custom key-value pair to the result map.
	 * <p>
	 * Use this for additional metadata such as pagination info (e.g., {@code "nextListSize"}).
	 * </p>
	 *
	 * @param key   the key under which the value is stored in the result map
	 * @param value the value to associate with the given key
	 */
	public void addResult(String key, Object value) {
		result.put(key, value);
	}

}