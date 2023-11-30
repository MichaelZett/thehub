package de.zettsystems.hub.common.adapter;

import java.util.ArrayList;
import java.util.UUID;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import de.zettsystems.hub.common.values.ErrorProperties;
import de.zettsystems.hub.common.values.ExceptionResponseData;
import de.zettsystems.hub.common.values.NoSuchElementFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	private final ErrorProperties errorProperties;

	@Override
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ExceptionResponseData errorResponse = new ExceptionResponseData(UUID.randomUUID(),
				HttpStatus.UNPROCESSABLE_ENTITY.value(), "Validation error. Check 'errors' field for details.", null,
				new ArrayList<>());
		for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
		}
		LOG.warn("Validation error occurred: {}", ex);
		return ResponseEntity.unprocessableEntity().body(errorResponse);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {
		LOG.error("Some unknown error occurred: {}", ex);
		return buildErrorResponse(ex, statusCode, request);
	}

	@ExceptionHandler(NoSuchElementFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleNoSuchElementFoundException(NoSuchElementFoundException ex,
			WebRequest request) {
		LOG.warn("Element not found: {}", ex);
		return buildErrorResponse(ex, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
		LOG.error("Some unknown error occurred: {}", exception);
		return buildErrorResponse(exception, "Unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	private ResponseEntity<Object> buildErrorResponse(Exception exception, HttpStatusCode httpStatus,
			WebRequest request) {
		return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);
	}

	private ResponseEntity<Object> buildErrorResponse(Exception exception, String message, HttpStatusCode httpStatus,
			WebRequest request) {
		final String stackTrace = errorProperties.stacktrace() ? ExceptionUtils.getStackTrace(exception) : null;
		ExceptionResponseData errorResponse = new ExceptionResponseData(UUID.randomUUID(), httpStatus.value(), message,
				stackTrace, null);

		return ResponseEntity.status(httpStatus).body(errorResponse);
	}

}
