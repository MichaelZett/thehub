package de.zettsystems.hub.security.domain;

import java.util.List;

import de.zettsystems.hub.security.values.CreateUserDTO;
import de.zettsystems.hub.security.values.Role;
import de.zettsystems.hub.security.values.UpdateUserDTO;
import de.zettsystems.hub.security.values.UserDTO;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Column(nullable = false)
	private String firstName;
	@NotBlank
	@Column(nullable = false)
	private String lastName;
	@NotBlank
	@Email
	@Column(nullable = false, unique = true)
	private String email;
	@NotBlank
	@Column(nullable = false)
	private String password;

	@NotNull
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private List<Role> roles;

	public User(String firstName, String lastName, String email, String password, List<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public void update(String firstName, String lastName, String email, String password, List<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public UserDTO toUserDTO() {
		return new UserDTO(id, firstName, lastName, email, roles);
	}

	public UpdateUserDTO toUpdateUserDTO() {
		return new UpdateUserDTO(id, firstName, lastName, email, password, roles);
	}

	public CreateUserDTO toCreateUserDTO() {
		return new CreateUserDTO(firstName, lastName, email, password, roles);
	}

}