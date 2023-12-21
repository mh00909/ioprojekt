package com.ioproject.reservetheweather.api;

import com.ioproject.reservetheweather.model.Event;
import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.repository.UserRepository;
import com.ioproject.reservetheweather.service.EventService;
import com.ioproject.reservetheweather.service.UserService;
import com.ioproject.reservetheweather.service.WeatherService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;
    @MockBean
    private EventService eventService;
    @MockBean
    private WeatherService weatherService;
    @Mock
    private Authentication authentication;
    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    void setUp() {
        SecurityContextHolder.setContext(securityContext);
        User mockUser = new User(20L, "name", "mail@gmail.com", "password", 123321111L, "USER");

        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getPrincipal()).thenReturn(mockUser);
        when(userRepository.findUserByName("name")).thenReturn(Optional.of(mockUser));
    }

    // testy getmydetails
    @Test
    void testGetMyDetails() throws Exception {
        mockMvc.perform(get("/api/user/Account"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    void testShowMyEvents() throws Exception {
        when(userService.showMyEvents(anyLong())).thenReturn(Arrays.asList(new Event(), new Event()));
        mockMvc.perform(get("/api/user/myevents"))
                .andExpect(status().isOk());
    }


    @Test
    void testMyEventsOnDay() throws Exception {
        when(userService.showMyEvents(anyLong())).thenReturn(List.of(new Event()));

        LocalDate localDate = LocalDate.parse("2024-05-09");
        mockMvc.perform(get("/api/user/myEventsOnDay")
                        .param("date", String.valueOf(localDate))
                        .param("name", "name"))
                .andExpect(status().isOk());
    }

    @Test
    void testSignUpForEvent_UserNotFound() throws Exception {
        Long eventId = 1L;
        String name = "nonExistingUser";
        when(userRepository.findUserByName(name)).thenReturn(Optional.empty());
        mockMvc.perform(post("/api/user/events/signup")
                        .param("eventid", eventId.toString())
                        .param("name", name))
                .andExpect(status().isNotFound());
    }

    @Test
    void testResignEvent() throws Exception {
        Long eventId = 1L;
        mockMvc.perform(post("/api/user/myevents/cancell")
                        .param("id", eventId.toString()))
                .andExpect(status().isOk());
    }



    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }
}
