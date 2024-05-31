package com.juliaosystem;


import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class UsuariosApplication {


	public static void main(String[] args) {
		SpringApplication.run(UsuariosApplication.class, args);
	}


	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder()
				.group("springshop-public")
				.packagesToScan("com.juliaosystem")
				.build();
	}


}
