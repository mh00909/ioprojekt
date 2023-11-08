package com.ioproject.reservetheweather.api;

import com.ioproject.reservetheweather.model.Event;
import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import com.ioproject.reservetheweather.service.EventService;
import com.ioproject.reservetheweather.service.UserService;
import com.ioproject.reservetheweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/api/events/all")
    public ResponseEntity<Object> getAllEvents() {
        return ResponseEntity.ok(eventRepository.findAll());
    }

    @GetMapping("/api/events/description")
    public void showEventDescription(@RequestParam Long eventid){
        Optional<Event> event = eventRepository.findById(eventid);
        if(event.isPresent()){
            eventService.showDescription(event.get());
        }
    }


    @PostMapping("/api/events/signup/{eventid}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public void signUpForEvent(@RequestParam Long eventid){
        Optional<User> user = userRepository.findUserByMail(getLoggedIn().getUsername());
        if(user.isPresent()) {
            eventService.addPerson(eventid, user.get());
            userService.joinEvent(eventid, user.get());
        }
    }

    @PostMapping("api/user/myevents/cancell")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public void resignEvent(@RequestParam Long id){
        eventService.resign(id, userRepository.findUserByMail(getLoggedIn().getUsername()));
        userService.resign(id, userRepository.findUserByMail(getLoggedIn().getUsername()));
    }

    @PostMapping("api/user/myevents/discount")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public void discountEvent(@RequestParam Long id){
        eventService.discount(id, userRepository.findUserByMail(getLoggedIn().getUsername()));
    }


    @PostMapping("/api/user/myevents/reschedule")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public void rescheduleEvent(@RequestParam Long eventId, @RequestParam String date1){

        eventService.reschedule(eventId, userRepository.findUserByMail(getLoggedIn().getUsername()), date1);
    }




    public UserDetails getLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }



}