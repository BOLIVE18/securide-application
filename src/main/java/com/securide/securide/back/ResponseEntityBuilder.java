package com.securide.securide.back;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import io.vavr.control.Either;

public class ResponseEntityBuilder {
	private ResponseEntityBuilder() {
	}

	public static ResponseEntity matchEitherOnGet(Either<FunctionalIssue, ?> either) {
		return either.fold(ResponseEntityBuilder::createError, ResponseEntityBuilder::createGetResponseEntity);
	}

	public static ResponseEntity matchEitherOnPost(Either<FunctionalIssue, ?> either) {
		return either.fold(ResponseEntityBuilder::createError, ResponseEntityBuilder::createPostResponseEntity);
	}

	public static ResponseEntity matchEitherOnPut(Either<FunctionalIssue, ?> either) {
		return either.fold(ResponseEntityBuilder::createError, ResponseEntityBuilder::createPutResponseEntity);
	}

	public static <T> ResponseEntity matchEither(Either<FunctionalIssue, SimpleHttpResponse<T>> either) {
		return either.fold(ResponseEntityBuilder::createError, ResponseEntityBuilder::createResponseEntity);
	}

	public static <T extends Object> ResponseEntity<T> createGetResponseEntity(T body) {
		if (body == null || checkBodyEmpty(body)) {
			return ResponseEntity.noContent().build();
		}

		if (isPartialContent(body)) {
			return new ResponseEntity<T>(body, HttpStatus.PARTIAL_CONTENT);
		}

		return ResponseEntity.ok().body(body);
	}

	public static <T extends Object> ResponseEntity<T> createPostResponseEntity(T body) {
		return ResponseEntity.status(HttpStatus.CREATED).body(body);
	}

	public static <T extends Object> ResponseEntity<T> createPutResponseEntity(T body) {
		return ResponseEntity.ok().body(body);
	}

	public static ResponseEntity createResponseEntity(SimpleHttpResponse response) {
		if (response == null) {
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.status(response.getCode()).body(response.getBody());
		}
	}

	public static ResponseEntity<ErrorResponse> createError(FunctionalIssue issue) {
		return ResponseEntity.status(issue.getStatus()).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.body(new ErrorResponse<String>(issue.getMessage()));
	}

	/**
	 * Apply the method "isEmpty()" on the object if applicable
	 *
	 * @param <T>
	 * @param body
	 * @return true if body is empty, false if not empty or not applicable
	 */
	private static <T> boolean checkBodyEmpty(T body) {
		try {
			Method emptyMethod = body.getClass().getMethod("isEmpty");
			if (emptyMethod.getReturnType().isAssignableFrom(Boolean.class)
					|| emptyMethod.getReturnType().isAssignableFrom(boolean.class)) {
				return (boolean) emptyMethod.invoke(body);
			}
		}
		catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
		}
		return false;
	}

	/**
	 * Apply the method "getTotalPages()" on the object if applicable and check body contains more than 1 page
	 *
	 * @param <T>
	 * @param body
	 * @return true if body contains more than 1 page, false if more or not applicable
	 */
	private static <T> boolean isPartialContent(T body) {
		try {
			Method getTotalPagesMethod = body.getClass().getMethod("getTotalPages");
			if (getTotalPagesMethod.getReturnType().isAssignableFrom(Integer.class)
					|| getTotalPagesMethod.getReturnType().isAssignableFrom(int.class)) {
				return ((int) getTotalPagesMethod.invoke(body)) > 1;
			}
		}
		catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
		}
		return false;
	}
}