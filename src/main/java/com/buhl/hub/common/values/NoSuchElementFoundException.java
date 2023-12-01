package com.buhl.hub.common.values;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@SuppressWarnings("serial")
public class NoSuchElementFoundException extends ResponseStatusException {
	public NoSuchElementFoundException(String message) {
		super(HttpStatus.NOT_FOUND, message);
	}
}