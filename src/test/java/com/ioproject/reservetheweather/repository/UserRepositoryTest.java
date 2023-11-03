package com.ioproject.reservetheweather.repository;
import static org.assertj.core.api.Assertions.*;
import com.ioproject.reservetheweather.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserReposetory_addNewUser_returnSaved(){
        User user = User.builder().id(198L).name("user").mail("123wq").password("pass").phoneNumber(23421L).roles("USER").build();
        User saved = userRepository.save(user);
        assertThat(saved).isNotNull();
    }

    @Test
    public void UserRepositoryReturnsAllUsers(){
        User user1 = User.builder().id(18L).name("user123").mail("123wq").password("pass").phoneNumber(23421L).roles("USER").build();
        User user2 = User.builder().id(198L).name("user").mail("123wq").password("pass").phoneNumber(23421L).roles("USER").build();
        userRepository.save(user1);
        userRepository.save(user2);
        List<User> userList = userRepository.findAll();
        assertThat(userList).isNotNull();
        assertThat(userList.size()).isEqualTo(2);

    }

    @Test
    void checkIfUserExistsByEmail() {
        User user1 = User.builder().id(18L).name("user123").mail("123wq").password("pass").phoneNumber(23421L).roles("USER").build();

        userRepository.save(user1);
        Optional<User> exists = userRepository.findUserByMail("123wq");
        assertTrue(exists.isPresent());

    }

    @Test
    void findUserByMail() {
    }
}