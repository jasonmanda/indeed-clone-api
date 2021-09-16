package com.dash.it.solution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class KronosJobApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(KronosJobApiApplication.class, args);
	}

}
