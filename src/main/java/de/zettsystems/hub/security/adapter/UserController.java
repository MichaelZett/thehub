package de.zettsystems.hub.security.adapter;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.zettsystems.hub.security.application.UserService;
import de.zettsystems.hub.security.domain.User;
import de.zettsystems.hub.security.values.CreateUserDTO;
import de.zettsystems.hub.security.values.UpdateUserDTO;
import de.zettsystems.hub.security.values.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "the users API to do CRUD")
public class UserController {

	private final UserService userService;

	@Operation(summary = "Get all users")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "got all users", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }) })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<UserDTO> findAll() {
		return userService.getAll();
	}

	@Operation(summary = "Get a user by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "found the user", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "user not found", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> get(@PathVariable long id) {
		return ResponseEntity.of(Optional.ofNullable(userService.get(id)));
	}

	@Operation(summary = "Create a user")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "created the user", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid data supplied", content = @Content) })
	@PostMapping
	public ResponseEntity<User> create(@Valid @RequestBody CreateUserDTO userDTO) {
		long userId = userService.save(userDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userId).toUri();
		return ResponseEntity.created(location).build();
	}

	@Operation(summary = "Update a user by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "updated the user", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "user not found", content = @Content) })
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable long id, @Valid @RequestBody UpdateUserDTO userDTO) {
		userService.update(userDTO);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "Delete a user by id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "user with given id deleted or did not exist", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content) })
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

}