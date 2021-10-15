package io.github.odeivissonsantos;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BarbersystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarbersystemApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenApi() {
		return new OpenAPI()
			.info(
				new Info()
					.title("API - Barber System APP")
					.description("Api para controle de gestão para barbearias")
					.contact(new Contact()
						.name("Deivisson Santos")
						.email("deivissonsantos@hotmail.com")
					)
					.version("V2")
					.license(new License()
						.name("Apache 2.0")
					)
			);
	}

}
