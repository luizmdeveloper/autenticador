package br.com.vr.autenticador.application.infrastructure.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import java.util.Arrays;

@Configuration
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "BasicAuth", scheme = "basic")
public class OpenApiConfig {

    @Value("${miniautorizador.openai.dev-url}")
    private String urlDesenvolvimento;

    @Value("${miniautorizador.openai.prod-url}")
    private String urlProducao;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(urlDesenvolvimento);
        devServer.setDescription("Server URL in Development environment");

        Server prodServer = new Server();
        prodServer.setUrl(urlProducao);
        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("luiz.cavalcante@vr.com.br");
        contact.setName("Luiz Mário");
        contact.setUrl("https://www.vr.com.br");

        License mitLicense = new License().name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info()
                .title("Mini autorizador")
                .version("1.0")
                .contact(contact)
                .description("Api exemplo de mini autorizador de transação em um cartão de benefícios.").termsOfService("https://www.vr.com.br")
                .license(mitLicense);

        return new OpenAPI().info(info).servers(Arrays.asList(devServer, prodServer));
    }
}
