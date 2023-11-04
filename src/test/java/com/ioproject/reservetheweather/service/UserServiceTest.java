package com.ioproject.reservetheweather.service;

import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

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
        User user = User.builder().name("Jacek").mail(email).password("abc").phoneNumber(213131L).build();
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


}