package com.ioproject.reservetheweather.repository;

import com.ioproject.reservetheweather.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    void checkIfUserExistsByEmail() {
        String email = "user1@gmail.com";
        User user = new User("Jacek", email, "password", 123456789);
        userRepository.save(user);
        Optional<User> exists = userRepository.findUserByMail(email);
        assertTrue(exists.isPresent());
    }
}