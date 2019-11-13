package com.securide.securide.back;

import org.springframework.http.HttpStatus;

import io.vavr.control.Either;

public interface FunctionalIssue {
	String getMessage();

	HttpStatus getStatus();

	public default <T> Either<FunctionalIssue, T> toLeft() {
		return Either.left(this);
	}
}