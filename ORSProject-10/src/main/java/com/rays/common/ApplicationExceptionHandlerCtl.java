package com.rays.common;

import org.springframework.dao.DataAccessResourceFailureException;
import org.hibernate.exception.JDBCConnectionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler for the application.
 * <p>
 * This class intercepts exceptions thrown across all controllers and returns
 * appropriate HTTP responses with structured {@link ORSResponse} bodies.
 * It separates database-related failures from general runtime errors.
 * </p>
 *
 * @author malay dongre
 */
@RestControllerAdvice
public class ApplicationExceptionHandlerCtl {

    /**
     * Handles all database connectivity and transaction-related exceptions.
     * <p>
     * Catches the following exception types:
     * <ul>
     *   <li>{@link CannotCreateTransactionException} - thrown when a transaction cannot be opened</li>
     *   <li>{@link DataAccessResourceFailureException} - thrown on data source/resource failure</li>
     *   <li>{@link JDBCConnectionException} - thrown on JDBC connection issues</li>
     * </ul>
     * </p>
     *
     * @param e the exception that was thrown; one of the three database-related exception types
     * @return a {@link ResponseEntity} containing an {@link ORSResponse} with {@code success=false}
     *         and a user-friendly message, with HTTP status {@code 503 SERVICE_UNAVAILABLE}
     */
    @ExceptionHandler({
        CannotCreateTransactionException.class,
        DataAccessResourceFailureException.class,
        JDBCConnectionException.class
    })
    public ResponseEntity<ORSResponse> handleDatabaseException(Exception e) {

        ORSResponse res = new ORSResponse(false);
        res.addMessage("Database service is currently unavailable. Please try again later.");

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)   // 503
                .body(res);
    }

    /**
     * Handles all general runtime exceptions not covered by other handlers.
     * <p>
     * Acts as a catch-all for any {@link RuntimeException} propagated from the service
     * or DAO layers. Returns the exception's message directly in the response body.
     * </p>
     *
     * @param e the {@link RuntimeException} that was thrown
     * @return a {@link ResponseEntity} containing an {@link ORSResponse} with {@code success=false}
     *         and the exception's message, with HTTP status {@code 500 INTERNAL_SERVER_ERROR}
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ORSResponse> handleRuntimeException(RuntimeException e) {

        ORSResponse res = new ORSResponse(false);
        res.addMessage(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR) // 500
                .body(res);
    }
}