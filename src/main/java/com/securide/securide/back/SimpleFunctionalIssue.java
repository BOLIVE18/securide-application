package com.securide.securide.back;
import org.springframework.http.HttpStatus;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

@EqualsAndHashCode
public class SimpleFunctionalIssue implements FunctionalIssue {
	private HttpStatus status;
	private String[] message;

	public SimpleFunctionalIssue(@NonNull HttpStatus status, @NonNull String... messages) {
		this.status = status;
		this.message = messages;
	}

	@Override
	public String getMessage() {
		return (this.message != null ? String.join(" , ", message) : "");
	}

	@Override
	public HttpStatus getStatus() {
		return status;
	}
}