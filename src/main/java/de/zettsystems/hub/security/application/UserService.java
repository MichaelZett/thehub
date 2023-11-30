package de.zettsystems.hub.security.application;

import java.util.Collection;

import de.zettsystems.hub.security.values.CreateUserDTO;
import de.zettsystems.hub.security.values.UpdateUserDTO;
import de.zettsystems.hub.security.values.UserDTO;

public interface UserService {

	Long save(CreateUserDTO user);

	Collection<UserDTO> getAll();

	UserDTO get(long id);

	void update(UpdateUserDTO userDTO);

	void delete(long id);

}
