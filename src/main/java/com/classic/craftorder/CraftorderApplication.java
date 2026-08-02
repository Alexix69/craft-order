package com.classic.craftorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CraftorderApplication {

	public static void main(String[] args) {
		java.util.TimeZone.setDefault(
			java.util.TimeZone.getTimeZone("America/Guayaquil"));
		SpringApplication.run(CraftorderApplication.class, args);
	}

}
