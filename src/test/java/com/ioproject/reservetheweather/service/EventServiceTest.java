package com.ioproject.reservetheweather.service;

import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.model.WeatherData;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.model.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EventServiceTest {
    @Mock
    private EventRepository eventRepository;

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private EventService eventService;

    @Test
    void getEventsTest() {
        List<Event> mockEvents = Arrays.asList(new Event(), new Event());
        when(eventRepository.findAll()).thenReturn(mockEvents);
        List<Event> events = eventService.getEvents();
        assertEquals(2, events.size());
    }

    // testy checkweather
    @Test
    void checkWeatherTest_NoEvent() {
        Long eventId = 1L;
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
        assertFalse(eventService.checkWeather(eventId));
    }
    @Test
    void checkWeatherTest_badWeather() {
        Long eventId = 1L;
        Event event = new Event();
        event.setMaxTemperature(30);
        event.setMinTemperature(10);
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        WeatherData wd = new WeatherData();
        wd.setMain(new WeatherData.MainWeatherData());
        wd.getMain().setTemp(50); // zbyt wysoka
        when(weatherService.checkWeather()).thenReturn(wd);

        assertTrue(eventService.checkWeather(eventId));
        assertTrue(event.isBadWeather());
    }
    @Test
    void badWeatherInformTest() {
        User user = new User();
        Event event = new Event();
        event.setTime(LocalTime.now());
        String message = eventService.badWeatherInfrom(user, event);
        assertTrue(message.contains("przewidywana jest zła pogoda"));
    }

    @Test
    void addEventTest() {
        Event event = new Event();
        when(eventRepository.save(event)).thenReturn(event);
        assertTrue(eventService.addEvent(event));
    }

    @Test
    void resignTest_emptyUser() {
        assertFalse(eventService.resign(1L, Optional.empty()));
    }

    @Test
    void discountTest_emptyUser() {
        assertFalse(eventService.discount(1L, Optional.empty()));
    }

    // testy addPerson
    @Test
    void addPersonTest_OK(){
        Long eventId = 1L;
        User user = new User();
        Event event = new Event();
        event.setMaxUsers(20);
        event.setUsers(new ArrayList<>());
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));

        int result = eventService.addPerson(eventId, user);

        assertEquals(1, result); // 1 oznacza sukces
    }
    @Test
    void addPersonTest_eventNotFound() {
        Long eventId = 1L;
        User user = new User();
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
        int result = eventService.addPerson(eventId, user);
        assertEquals(2, result); // 2 oznacza brak podanych zajęć
    }
    @Test
    void addPersonTest_maxUsers(){
        Long eventId = 1L;
        User user = new User();
        Event event = new Event();
        event.setSignedUsers(0);
        event.setUsers(new ArrayList<>());
        event.setMaxUsers(1);
        event.addNewUser(new User());
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        int result = eventService.addPerson(eventId, user);

        assertEquals(3, result); // 3 oznacza brak miejsc
    }



    // testy rescheduleEvent
    @Test
    void rescheduleEventTest_OK(){
        Long eventId = 1L;
        LocalDate newDate = LocalDate.now();
        LocalTime newTime = LocalTime.now();
        Event event = new Event();
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        assertTrue(eventService.rescheduleEvent(eventId, newDate, newTime));
        assertEquals(newDate, event.getDate());
        assertEquals(newTime, event.getTime());
    }
    @Test
    void rescheduleEventTest_eventNotFound() {
        Long eventId = 1L;
        LocalDate newDate = LocalDate.now();
        LocalTime newTime = LocalTime.now();
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
        assertFalse(eventService.rescheduleEvent(eventId, newDate, newTime));
    }

    //testy rescheduleUser
    @Test
    void rescheduleUserTest_OK(){
        Long eventId = 5L;
        String newDate = "2024-05-01";
        User user = new User();
        Event event = new Event();
        user.setMyEvents(new ArrayList<>());
        event.setDate(LocalDate.parse("2024-04-12")); // zmiana wcześniej niż 24 godziny
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        assertTrue(eventService.rescheduleUser(eventId, Optional.of(user), newDate));
    }
    @Test
    void rescheduleUserTest_userEmpty() {
        Long eventId = 1L;
        String newDate = "2023-05-01";
        assertFalse(eventService.rescheduleUser(eventId, Optional.empty(), newDate));
    }

    @Test
    void rescheduleUser_eventEmpty() {
        Long eventId = 111L;
        String newDate = "2024-08-07";
        User user = new User();
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
        assertFalse(eventService.rescheduleUser(eventId, Optional.of(user), newDate));
    }
    @Test
    void rescheduleUser_notAllowed(){
        Long eventId = 1L;
        String newDate = "2023-05-01";
        User user = new User();
        user.setMyEvents(new ArrayList<>());
        Event event = new Event();
        event.setDate(LocalDate.now());
        event.setTime(LocalTime.now().plusHours(2)); // próba przełożenia na 2 godziny przed zajęciami
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        assertFalse(eventService.rescheduleUser(eventId, Optional.of(user), newDate));
    }

    // testy removeevent
    @Test
    void removeEventTest_OK(){
        Long eventId = 10L;
        Event event = new Event();
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        doNothing().when(eventRepository).delete(event);
        assertTrue(eventService.removeEvent(eventId));
        verify(eventRepository).delete(event);
    }
    @Test
    void removeEventTest_notFound() {
        Long eventId = 99L;
        when(eventRepository.findById(eventId)).thenReturn(Optional.empty());
        assertFalse(eventService.removeEvent(eventId));
    }



    //testy discount
    @Test
    void discountTest_OK(){
        Long eventId = 7L;
        User user = new User();
        Event event = new Event();
        event.setDiscount(false);
        event.setDate(LocalDate.now().plusDays(7));
        event.setTime(LocalTime.now());
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        assertTrue(eventService.discount(eventId, Optional.of(user)));
    }
    @Test
    void discountTest_tooLate(){
        Long eventId = 7L;
        User user = new User();
        Event event = new Event();
        event.setDiscount(false);
        event.setDate(LocalDate.now());
        event.setTime(LocalTime.now().plusHours(5));
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        assertFalse(eventService.discount(eventId, Optional.of(user)));
    }

    @Test
    void discountTest_alreadyDiscounted() {
        Long eventId = 7L;
        User user = new User();
        Event event = new Event();
        event.setDate(LocalDate.now().plusDays(3));
        event.setDiscount(true);

        ArrayList<Event> events = new ArrayList<>();
        events.add(event);
        user.setMyEvents(events);

        when(eventRepository.findById(eventId)).thenReturn(Optional.of(event));
        assertFalse(eventService.discount(eventId, Optional.of(user)));
    }

}