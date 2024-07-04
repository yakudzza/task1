package com.example.task1.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Method Tracking API", version = "1.0", description = "API for tracking method execution times"))
public class SwaggerConfig {
}