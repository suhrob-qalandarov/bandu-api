package org.exp.banduapp.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "BANDU",
                version = "v1",
                description = "Online joyni band qiluvchi dastur. Foydalanuvchi ro‘yxatdan o‘tadi, xizmat joylarini ko‘radi, band qiladi va o‘z bandlarini boshqaradi."
        ),
        servers = @Server(url = "http://localhost:8080")
)
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        final String SCHEME_NAME = "Authorization";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(SCHEME_NAME))
                .components(new Components().addSecuritySchemes(SCHEME_NAME,
                        new SecurityScheme()
                                .name(SCHEME_NAME)
                                .type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER)
                ));
    }
}
