package com.buhl.hub.customer.values;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Customer", description = "Kundendaten")
public record CustomerDTO(@Schema(description = "name") @NotBlank String name) {
}
