package com.ioproject.reservetheweather;

import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import com.ioproject.reservetheweather.service.AccountService;
import com.ioproject.reservetheweather.service.EventService;
import com.ioproject.reservetheweather.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReservetheweatherApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;
	@Autowired
	private EventService eventService;


	private User user;
	private User admin;
	@BeforeEach
	public void init(){
		user = User.builder().name("Jacek Nowak").mail("user123@gmail.com").password("abc").phoneNumber(123456789L).roles("USER").build();
		admin = User.builder().name("Eliza Pancakes").mail("eliza@gmail.com").password("abc").phoneNumber(213131L).roles("ADMIN").build();
	}
	@Test
	public void test1(){


	}

}
