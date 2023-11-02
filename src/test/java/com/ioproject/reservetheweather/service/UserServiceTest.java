package com.ioproject.reservetheweather.service;

import com.ioproject.reservetheweather.entity.User;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@Transactional
class UserServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private EventRepository eventRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @InjectMocks private UserService underTest;

    @Test
    void getUsersTest() {
        underTest.getUsers();
        Mockito.verify(userRepository).findAll();
    }

    @Test
    void addNewUserTest() {
        String email = "user1@gmail.com";
        User user = new User("Jacek", email, "password", 123456789);

        underTest.addNewUser(user);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertEquals(capturedUser.getId(), user.getId());


    }

    @Test
    void dontInsertWithTheSameEmail(){
        String email = "ewa@gmail.com";
        User existingUser = new User("Ewa Nowak", email, "password", 123456789);
        Mockito.when(userRepository.findUserByMail(email)).thenReturn(Optional.of(existingUser));

        User userToInsert = new User("Ewa Kowalik", email, "abc", 123456789);

        boolean inserted = underTest.addNewUser(userToInsert);

        assertFalse(inserted);
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteSelf() {
    }

    @Test
    void signUp() {
    }

    @Test
    void showMyEvents() {
    }

    @Test
    void joinEvent() {
    }
}