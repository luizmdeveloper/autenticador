package br.com.vr.autenticador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AutenticadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutenticadorApplication.class, args);
	}

}
