package de.zettsystems.hub.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition( //
		info = @Info( //
				title = "The Hub Api", //
				version = "0.0.1", //
				description = "The API for the Hub", //
				contact = @Contact(url = "https://github.com/MichaelZett", name = "Michael Zöller", email = "michael2.zoeller@gmail.com")))
public class SwaggerConfig {

}