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

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    private final UserService userService;
    private final EventService eventService;
    private final WeatherService weatherService;

    @Autowired
    public AdminController(UserService userService, EventService eventService, WeatherService weatherService) {
        this.userService = userService;
        this.weatherService = weatherService;
        this.eventService = eventService;
    }
    @GetMapping("/allusers")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }


    @PostMapping("/addEvent")
    public ResponseEntity<Object> addEvent(@RequestParam("date") LocalDate date,
                                           @RequestParam("time")LocalTime eventTime,
                                           @RequestParam("duration") int duration,
                                           @RequestParam("description") String description,
                                           @RequestParam("name") String name,
                                           @RequestParam("maxUsers") int maxUsers,
                                           @RequestParam("location") String eventLocation,
                                           @RequestParam("price") double eventPrice,
                                           @RequestParam("minTemperature") double mintemp,
                                           @RequestParam("maxTemperature") double maxtemp){
        Event event = new Event(date, eventTime, duration, description, name,
                maxUsers, eventLocation, eventPrice, mintemp, maxtemp);

        eventService.addEvent(event);
        return ResponseEntity.ok("Dodano zajęcia");
    }

    @RequestMapping(value = "/removeEvent", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Object> removeEvent(@RequestParam Event event){
        boolean removed = eventService.removeEvent(event.getId());
        if(removed){
            return ResponseEntity.ok("Zrezygnowano z zajęć.");
        }
        return ResponseEntity.badRequest().body("Brak zajęć o podanym ID.");
    }

}
