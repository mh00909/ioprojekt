package com.ioproject.reservetheweather.api;
import com.ioproject.reservetheweather.auth.JwtService;
import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import com.ioproject.reservetheweather.service.EventService;
import com.ioproject.reservetheweather.service.UserService;
import com.ioproject.reservetheweather.service.WeatherService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerIntegrationTest {
    @Autowired
   private MockMvc mockMvc;
    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private EventRepository eventRepository;
    @MockBean
    private UserService userService;
    @MockBean
    private EventService eventService;
    @MockBean
    private WeatherService weatherService;
    @Test
    void testGetAllUsers() throws Exception {
        User user1 = new User(150L, "testUser1", "test1@wp.pl", "password", 123321213, "USER");
        User user2 = new User(150L, "testUser2", "test2@wp.pl", "password", 123321000, "USER");
        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(get("/api/admin/allusers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    // testy addEvent
    @Test
    void testAddEvent_OK() throws Exception{
        String url = "/api/admin/addEvent";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("date", "2024-03-01");
        params.add("time", "10:00");
        params.add("duration", "1.5");
        params.add("description", "Dla początkujących");
        params.add("name", "Joga na świeżym powietrzu");
        params.add("maxUsers", "20");
        params.add("location", "Kraków ul. Makowskiego 12");
        params.add("price", "15.0");
        params.add("minTemperature", "10.0");
        params.add("maxTemperature", "25.0");
        mockMvc.perform(post(url).params(params))
                .andExpect(status().isOk())
                .andExpect(content().string("Dodano zajęcia"));
    }

    @Test
    void testAddEvent_invalidParameters() throws Exception {
        String url = "/api/admin/addEvent";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("date", "abcd321");
        params.add("time", "25:00");
        mockMvc.perform(post(url).params(params))
                .andExpect(status().isBadRequest());
    }


    // testy reschedule
    @Test
    void testRescheduleEvent_OK() throws Exception {
        String url = "/api/admin/rescheduleEvent";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("eventID", "1");
        params.add("newTime", "11:00");
        params.add("newDate", "2024-11-02");
        when(eventService.rescheduleEvent(anyLong(), any(), any())).thenReturn(true);
        mockMvc.perform(post(url).params(params))
                .andExpect(status().isOk())
                .andExpect(content().string("Zmieniono termin zajęć"));
    }

    @Test
    void testRescheduleEven_notFound() throws Exception {
        String url = "/api/admin/rescheduleEvent";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("eventID", "999ABC");
        params.add("newTime", "12:00");
        params.add("newDate", "2024-12-01");
        when(eventService.rescheduleEvent(anyLong(), any(), any())).thenReturn(false);
        mockMvc.perform(post(url).params(params))
                .andExpect(status().isBadRequest());
    }

    // testy removeevent
    @Test
    void testRemoveEvent_OK() throws Exception {
        String url = "/api/admin/removeEvent";
        long eventId = 1L;
        when(eventService.removeEvent(eventId)).thenReturn(true);
        mockMvc.perform(post(url).param("eventID", String.valueOf(eventId)))
                .andExpect(status().isOk())
                .andExpect(content().string("Zrezygnowano z zajęć."));
    }

    @Test
    void testRemoveEvent_notFound() throws Exception {
        String url = "/api/admin/removeEvent";
        long eventId = 999L;
        when(eventService.removeEvent(eventId)).thenReturn(false);
        mockMvc.perform(post(url).param("eventID", String.valueOf(eventId)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Brak zajęć o podanym ID."));
    }


}
