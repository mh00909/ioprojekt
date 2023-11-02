package com.ioproject.reservetheweather.repository;

import com.ioproject.reservetheweather.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository underTest;
    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }
    @Test
    void checkIfUserExistsByEmail() {
        String email = "user1@gmail.com";
        User user = new User("Jacek", email, "password", 123456789);
        underTest.save(user);
        Optional<User> exists = underTest.findUserByMail(email);
        assertTrue(exists.isPresent());

        // e-mail którego nie ma w bazie
        String email2 = "anothermail";
        Optional<User> doesntexists = underTest.findUserByMail(email2);
        assertFalse(doesntexists.isPresent());
    }
}