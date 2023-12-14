package com.ioproject.reservetheweather.service;

import com.ioproject.reservetheweather.model.Event;
import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Transactional
class UserServiceTest {

    @Mock private UserRepository userRepository;  // mockowane repozytoria
    @Mock private EventRepository eventRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @InjectMocks private UserService underTest; // testowana klasa

    @Test
    void getUsersTest() {
        underTest.getUsers();
        Mockito.verify(userRepository).findAll();
    }

    // testy addNewUser()
    @Test
    void addNewUserTest_OK() {
        String email = "jacekd1321@gmail.com";
        User user = User.builder().name("Jacek321").mail(email).password("abc123321").phoneNumber(213131L).build();
        underTest.addNewUser(user);

        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertEquals(capturedUser.getId(), user.getId());

    }
    @Test
    void addNewUserTest_sameEmail(){
        String email = "ewa@gmail.com";
        User existingUser = new User("Ewa Nowak", email, "password", 123456789);
        Mockito.when(userRepository.findUserByMail(email)).thenReturn(Optional.of(existingUser));

        User userToInsert = new User("Ewa Kowalik", email, "abc", 123456789);

        int inserted = underTest.addNewUser(userToInsert);

        assertEquals(0, inserted);

    }

    // testy deleteUser()
    @Test
    void deleteUserTest_userExist(){
        User user = new User();
        String login = "user123";
        Mockito.when(userRepository.findUserByName(login)).thenReturn(Optional.of(user));

        boolean result = underTest.deleteUser(login);
        Mockito.verify(userRepository).delete(user);
        assertTrue(result);
    }
    @Test
    void deleteUserTest_userDoesntExist(){
        User user = new User();
        String login = "user123";
        Mockito.when(userRepository.findUserByName(login)).thenReturn(Optional.empty());

        assertThrows(IllegalStateException.class, ()->underTest.deleteUser(login)); // rzucany wyjątek przy próbie usunięcia
    }

    // testy updateUserMail()
    @Test
    void updateUserMailTest_OK(){
        Long userID = 1L;
        String newEmail = "abc321@gmail.com";
        User user = new User();
        Mockito.when(userRepository.findById(userID)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findUserByMail(newEmail)).thenReturn(Optional.empty());
        boolean result = underTest.updateUserMail(userID, newEmail);
        assertEquals(newEmail, user.getMail());
        assertTrue(result);
    }
    @Test
    void updateUserMailTest_taken() {
        Long userID = 1L;
        String newEmail = "user@interia.com";
        User user = new User();
        User user2 = new User();
        Mockito.when(userRepository.findById(userID)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findUserByMail(newEmail)).thenReturn(Optional.of(user2));
        assertThrows(IllegalStateException.class, () -> underTest.updateUserMail(userID, newEmail));
    }


    // testy updateUserName
    @Test
    void updateUserNameTest_OK() {
        Long userID = 1L;
        String login = "jacek123";
        User user = new User();
        Mockito.when(userRepository.findById(userID)).thenReturn(Optional.of(user));
        boolean result = underTest.updateUserName(userID, login);
        assertEquals(login, user.getName());
        assertTrue(result);
    }
    @Test
    void updateUserNameTest_taken() {
        Long userID = 1L;
        String login = "jacek12";
        User user = new User();
        User user2 = new User();
        Mockito.when(userRepository.findById(userID)).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findUserByName(login)).thenReturn(Optional.of(user2));
        assertThrows(IllegalStateException.class, () -> underTest.updateUserName(userID, login));
    }

    // testy showMyEvents()
    @Test
    void showMyEventsTest(){
        Long userID = 1L;
        User user = Mockito.mock(User.class);
        List<Event> mockEvents = Arrays.asList(new Event(), new Event());
        Mockito.when(user.getMyEvents()).thenReturn(mockEvents);
        Mockito.when(userRepository.findById(userID)).thenReturn(Optional.of(user));
        List<Event> events = underTest.showMyEvents(userID);
        assertEquals(mockEvents, events);
    }

    // testy joinEvent()
    @Test
    void joinEventTest_OK() {
        Long eventID = 1L;
        Event event = new Event();
        event.setTime(LocalTime.of(10, 0));
        event.setDuration(2);
        event.setDate(LocalDate.parse("2025-12-01"));
        User user = new User();
        user.setMyEvents(new ArrayList<>());

        Mockito.when(eventRepository.findById(eventID)).thenReturn(Optional.of(event));
        int result = underTest.joinEvent(eventID, user);
        assertEquals(3, result); // 3 oznacza, że udało się zapisać
        assertTrue(user.getMyEvents().contains(event));
    }
    @Test
    void joinEventTest_alreadyEnrolled() {
        Long eventID = 1L;
        Event event = new Event();
        User user = new User();
        List<Event> myEvents = new ArrayList<>();
        myEvents.add(event);  // użytkownik już zapisał się na te zajęcia
        user.setMyEvents(myEvents);

        Mockito.when(eventRepository.findById(eventID)).thenReturn(Optional.of(event));
        int result = underTest.joinEvent(eventID, user);
        assertEquals(1, result);  // 1 oznacza, że użytkownik jest już zapisany na dane zajęcia
    }

    @Test
    void joinEventTest_hasDifferentEvents() {
        Long eventID = 1L;
        Event newEvent = new Event();
        newEvent.setTime(LocalTime.of(10, 0));
        newEvent.setDuration(2);
        newEvent.setDate(LocalDate.parse("2024-06-06"));

        Event event2 = new Event();
        event2.setTime(LocalTime.of(11, 0));  // czasy się będą nakładać
        event2.setDuration(2);
        event2.setDate(LocalDate.parse("2024-06-06"));

        User user = new User();
        List<Event> myEvents = new ArrayList<>();
        myEvents.add(event2);
        user.setMyEvents(myEvents);

        Mockito.when(eventRepository.findById(eventID)).thenReturn(Optional.of(newEvent));
        int result = underTest.joinEvent(eventID, user);
        assertEquals(2, result);  // 2 oznacza, że użytkownik ma już inne zajęcia w tym czasie
    }
    @Test
    void joinEventTest_eventNotFound() {
        Long eventID = 1L;
        User user = new User();
        Mockito.when(eventRepository.findById(eventID)).thenReturn(Optional.empty());
        int result = underTest.joinEvent(eventID, user);
        assertEquals(0, result);  // 0 oznacza, że nie znaleziono podanych zajęć
    }


    // testy resign()
    @Test
    void resignTest_success() {
        Long eventID = 1L;
        User user = Mockito.mock(User.class);
        Event event = new Event();
        Mockito.when(eventRepository.findById(eventID)).thenReturn(Optional.of(event));
        Mockito.when(user.resign(event)).thenReturn(true);

        boolean result = underTest.resign(eventID, Optional.of(user));

        assertTrue(result);
    }

    @Test
    void resignTest_eventNotFound() {
        Long eventID = 1L;
        User user = new User();
        Mockito.when(eventRepository.findById(eventID)).thenReturn(Optional.empty());

        boolean result = underTest.resign(eventID, Optional.of(user));

        assertFalse(result);
    }

}