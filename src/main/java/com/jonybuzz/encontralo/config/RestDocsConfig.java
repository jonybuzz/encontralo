package com.jonybuzz.encontralo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(type = SecuritySchemeType.HTTP, scheme = "Basic")
@OpenAPIDefinition(info = @Info(
        title = "encontralo.com.ar",
        description = "API Rest Backend"))

public class RestDocsConfig {

}