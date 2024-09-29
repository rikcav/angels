package com.angels.gateway.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Universidade do Pernambuco - UPE", email = "lapes@upe.br", url = "https://github.com/upe-garanhuns"), description = "Gateway para uso dos Modelos Angels", title = "Gateway Angels", version = "1.0", license = @License(name = "Apache-2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"), termsOfService = "Termos de serviço"), servers = {
        @Server(description = "Local ENV", url = "http://localhost:8080"), @Server(description = "Server ENV", url = "http://0.0.0.0:8080") })
public class OpenApiConfig {
}
