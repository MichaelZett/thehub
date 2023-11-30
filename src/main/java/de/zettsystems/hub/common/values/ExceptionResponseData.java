package de.zettsystems.hub.common.values;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ExceptionResponseData(UUID uuid, int status, String message, String stackTrace,
		List<ValidationError> errors) {

	public void addValidationError(String field, String message) {
		errors.add(new ValidationError(field, message));
	}

	private static record ValidationError(String field, String message) {
	}
}
