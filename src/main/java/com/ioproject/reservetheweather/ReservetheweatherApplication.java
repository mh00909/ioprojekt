package com.ioproject.reservetheweather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.health.HealthContributorAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;

@EntityScan("com.ioproject.reservetheweather.model")
@Import({ HealthContributorAutoConfiguration.class })
@SpringBootApplication
public class ReservetheweatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservetheweatherApplication.class, args);
	}

}
