package de.zettsystems.hub.security.application;

import de.zettsystems.hub.common.values.NoSuchElementFoundException;

@SuppressWarnings("serial")
public class NoSuchUserException extends NoSuchElementFoundException {

	public NoSuchUserException(long id) {
		super("User with id %d not found".formatted(id));
	}

}
