package com.example.cassandrapaginationdemo.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun springShopOpenAPI(): OpenAPI? = OpenAPI()
        .info(
            Info().title("Cassandra Pagination Demo")
                .description("Demo app for cassandra pagination.")
                .version("v0.0.1")
        )
}
