package com.emailsender.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Api de envio de e-mails")
                        .version("1.0")
                        .description("Microserviço só para enviar e-mails com SpringBoot")
                        .contact(new Contact()
                                .name("Rudge")
                        )
                );
    }
}
