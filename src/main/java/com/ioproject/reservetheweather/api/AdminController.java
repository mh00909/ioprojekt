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


    @RequestMapping(value = "/addEvent", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Object> addEvent(@ModelAttribute Event event){
        eventService.addEvent(event);
        return ResponseEntity.ok("Dodano wydarzenie");
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
