package com.CabbageAndGarlic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class CabbageAndGarlicApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabbageAndGarlicApplication.class, args);
	}

}
