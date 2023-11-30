package de.zettsystems.hub.security.application;

import java.util.Collection;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.zettsystems.hub.security.domain.User;
import de.zettsystems.hub.security.domain.UserRepository;
import de.zettsystems.hub.security.values.CreateUserDTO;
import de.zettsystems.hub.security.values.UpdateUserDTO;
import de.zettsystems.hub.security.values.UserDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;

	@Override
	@Transactional(readOnly = false)
	public Long save(CreateUserDTO user) {
		User newUser = new User(user.firstName(), user.lastName(), user.email(), encoder.encode(user.password()),
				user.roles());
		return userRepository.save(newUser).getId();
	}

	@Override
	public Collection<UserDTO> getAll() {
		return userRepository.findAll().stream().map(u -> u.toUserDTO()).toList();
	}

	@Override
	public UserDTO get(long id) {
		return userRepository.findById(id).orElseThrow(() -> new NoSuchUserException(id)).toUserDTO();
	}

	@Override
	@Transactional(readOnly = false)
	public void update(UpdateUserDTO userDTO) {
		final User entity = userRepository.findById(userDTO.id())
				.orElseThrow(() -> new NoSuchUserException(userDTO.id()));
		entity.update(userDTO.firstName(), userDTO.lastName(), userDTO.email(), encoder.encode(userDTO.password()),
				userDTO.roles());
	}

	@Override
	@Transactional(readOnly = false)
	public void delete(long id) {
		userRepository.deleteById(id);
	}

}
