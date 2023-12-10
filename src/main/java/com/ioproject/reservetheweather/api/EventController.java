package com.ioproject.reservetheweather.api;
import com.ioproject.reservetheweather.model.Event;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import com.ioproject.reservetheweather.service.EventService;
import com.ioproject.reservetheweather.service.UserService;
import com.ioproject.reservetheweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping
public class EventController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    private final UserService userService;
    private final EventService eventService;
    private final WeatherService weatherService;

    public EventController(UserService userService, EventService eventService, WeatherService weatherService) {
        this.userService = userService;
        this.eventService = eventService;
        this.weatherService = weatherService;
    }

    // dostępne bez autoryzacji
    @GetMapping("/Main")
    public String hello() {
        return "Strona";
    }
    @GetMapping("/api/kontakt")
    public ResponseEntity<Object> kontakt(){
        String daneKontaktowe = "W przypadku problemów skontaktuj się z nami:\n e-mail: reservetheweather@gmail.com";
        return ResponseEntity.ok(daneKontaktowe);
    }

    @GetMapping("/api/events/all")
    public ResponseEntity<Object> getAllEvents() {
        return ResponseEntity.ok(eventRepository.findAll());
    }

    @GetMapping("/api/events/description/")
    public ResponseEntity<Object> showEventDescription(@RequestParam Long eventid){
        Optional<Event> event = eventRepository.findById(eventid);
        if(event.isPresent()){
            return ResponseEntity.ok(eventService.showDescription(event.get()));
        }
        return ResponseEntity.status(404).body("Nie znaleziono strony.");
    }




}
