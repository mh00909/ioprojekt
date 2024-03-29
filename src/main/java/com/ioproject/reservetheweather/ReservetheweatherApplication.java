package com.ioproject.reservetheweather;


import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@EntityScan("com.ioproject.reservetheweather.model")
@SpringBootApplication
public class ReservetheweatherApplication implements CommandLineRunner
{

	@Autowired
	private UserRepository userRepository;
	public static void main(String[] args) {
		SpringApplication.run(ReservetheweatherApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<User> adminAccount = userRepository.findUserByName("admin");
		if(!adminAccount.isPresent()){
			User user = new User();
			user.setRoles("ADMIN");
			user.setName("admin");
			user.setMail("admin@gmail.com");
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save(user);
		}
	}


}
