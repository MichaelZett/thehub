package com.buhl.hub.shop.adapter;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buhl.hub.shop.application.ShopService;
import com.buhl.hub.shop.values.ShopDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shops")
@RequiredArgsConstructor
@Tag(name = "Shops", description = "the shops API to do CRUD")
public class ShopController {

	private final ShopService shopsService;

	@Operation(summary = "Get all shops")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "got all users", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = ShopDTO.class)) }) })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<ShopDTO> findAll() {
		return shopsService.getAll();
	}

}