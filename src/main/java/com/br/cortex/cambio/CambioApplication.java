package com.br.cortex.cambio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CambioApplication {

	public static void main(String[] args) {
		SpringApplication.run(CambioApplication.class, args);
	}

}
