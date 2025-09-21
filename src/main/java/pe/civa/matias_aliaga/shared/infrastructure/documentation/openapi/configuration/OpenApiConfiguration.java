package pe.civa.matias_aliaga.shared.infrastructure.documentation.openapi.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI (Swagger) documentation.
 * Sets up the API documentation with title, description, version, and license information.
 * Currently configured without security requirements but includes commented security scheme setup.
 */
@Configuration
public class OpenApiConfiguration {

    /**
     * Creates and configures the OpenAPI specification for the Civa Bus API.
     * Defines the API metadata including title, description, version, and licensing.
     * Security configuration is commented out but available for future implementation.
     *
     * @return OpenAPI configuration object with complete API documentation setup
     */
    @Bean
    public OpenAPI learningPlatformOpenApi() {
        var openApi = new OpenAPI();
        openApi
                .info(new Info()
                        .title("Civa - Bus API")
                        .description("Technical Assessment API for Civa developed by Ethan Matias Aliaga Aguirre")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0")
                                .url("https://springdoc.org")));

        return openApi;
    }
}