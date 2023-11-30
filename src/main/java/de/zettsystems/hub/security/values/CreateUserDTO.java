package de.zettsystems.hub.security.values;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Useranlage", description = "Benutzerdaten zum Anlegen")
public record CreateUserDTO(@Schema(description = "Vorname") @NotBlank String firstName, //
		@Schema(description = "Nachname") @NotBlank String lastName, //
		@Schema(description = "E-Mail") @Email @NotBlank String email, //
		@Schema(description = "Passwort") @NotBlank String password, //
		@Schema(description = "Rollen") @NotNull @NotEmpty List<Role> roles) {
}
