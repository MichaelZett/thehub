package com.buhl.hub.customer.adapter;

import com.buhl.hub.customer.application.CustomerService;
import com.buhl.hub.customer.values.CustomerDTO;
import com.buhl.hub.shop.application.ShopService;
import com.buhl.hub.shop.values.ShopDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Tag(name = "Customers", description = "the customers API to do CRUD")
public class CustomerController {
	private final CustomerService customerService;

	@Operation(summary = "Get all customers")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "got all customers", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDTO.class)) }) })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Collection<CustomerDTO> findAll() {
		return customerService.getAll();
	}



}