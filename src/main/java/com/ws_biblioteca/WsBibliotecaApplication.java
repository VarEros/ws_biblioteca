package com.ws_biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com")
public class WsBibliotecaApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsBibliotecaApplication.class, args);
	}

}
