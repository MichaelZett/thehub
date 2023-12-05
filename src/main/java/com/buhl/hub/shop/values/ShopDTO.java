package com.buhl.hub.shop.values;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Shop", description = "Shopdaten")
public record ShopDTO(@Schema(description = "name") @NotBlank String name) {
}
