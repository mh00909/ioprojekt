package com.ioproject.reservetheweather;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@EntityScan("com.ioproject.reservetheweather.model")
@ComponentScan(basePackages = "com.ioproject.reservetheweather")
@SpringBootApplication
public class ReservetheweatherApplication //implements CommandLineRunner
{

	//@Autowired
	//private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(ReservetheweatherApplication.class, args);
	}
/*
	@Override
	public void run(String... args) throws Exception {
		Optional<User> adminAccount = userRepository.findAdmin("ADMIN");
		if(!adminAccount.isPresent()){
			User user = new User();
			user.setRoles("ADMIN");
			user.setName("admin");
			user.setMail("admin@gmail.com");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}

 */
}
