package com.rays.common;

/**
 * Thread-local holder for the current user's {@link UserContext}.
 * <p>
 * Provides a static utility to store and retrieve the {@link UserContext}
 * associated with the currently executing thread. This is typically used in
 * scenarios where passing the context explicitly through method parameters
 * is not convenient — for example, in security filters or interceptors that
 * set the context once per request.
 * </p>
 * <p>
 * <strong>Important:</strong> Always call {@link #clear()} after the request
 * is complete (e.g., in a filter's finally block) to prevent memory leaks
 * in thread-pool environments.
 * </p>
 *
 * @author Ajay Pratap Kerketta
 */
public class UserContextHolder {

	/**
	 * Thread-local storage for the {@link UserContext}.
	 * Each thread maintains its own independent copy of the context.
	 */
	private static final ThreadLocal<UserContext> threadLocal = new ThreadLocal<>();

	/**
	 * Stores the given {@link UserContext} in the current thread's local storage.
	 *
	 * @param context the {@link UserContext} to associate with the current thread; may be {@code null}
	 */
	public static void setContext(UserContext context) {
		threadLocal.set(context);
	}

	/**
	 * Retrieves the {@link UserContext} associated with the current thread.
	 *
	 * @return the {@link UserContext} for the current thread, or {@code null} if none has been set
	 */
	public static UserContext getContext() {
		return threadLocal.get();
	}

	/**
	 * Removes the {@link UserContext} from the current thread's local storage.
	 * <p>
	 * Should be called at the end of each request lifecycle to prevent memory leaks
	 * when threads are reused from a pool.
	 * </p>
	 */
	public static void clear() {
		threadLocal.remove();
	}
}