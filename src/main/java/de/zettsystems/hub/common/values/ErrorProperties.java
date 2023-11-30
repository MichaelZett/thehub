package de.zettsystems.hub.common.values;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "error")
public record ErrorProperties(boolean stacktrace) {

	@ConstructorBinding
	public ErrorProperties {
	}
}