package de.zettsystems.hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class TheHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheHubApplication.class, args);
	}

}
