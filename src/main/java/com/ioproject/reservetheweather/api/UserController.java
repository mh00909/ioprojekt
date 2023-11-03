package com.ioproject.reservetheweather.api;
import com.ioproject.reservetheweather.entity.Event;
import com.ioproject.reservetheweather.entity.User;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import com.ioproject.reservetheweather.service.EventService;
import com.ioproject.reservetheweather.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    private final UserService userService;
    private final EventService eventService;

    @Autowired
    public UserController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @GetMapping("/api")
    public String hello() {
        return "Strona domowa";
    }

    @PostMapping("/api/register")
    public ResponseEntity<Object> saveUser(@RequestBody User user) {
        if(userService.addNewUser(user)){
            return ResponseEntity.ok("Użytkownik zarejestrowany.");
        }
        return ResponseEntity.status(404).body("Podany e-mail jest już zajęty.");
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

    @PostMapping("/api/events/signup")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public void signUpForEvent(@RequestParam Long eventid){
        Optional<User> user = userRepository.findUserByMail(getLoggedIn().getUsername());
        if(user.isPresent()) {
            eventService.addPerson(eventid, user.get());
            userService.joinEvent(eventid, user.get());
        }
    }

    @PostMapping("/api/events/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> addEvent(@RequestBody Event event){
        eventService.addEvent(event);
        return ResponseEntity.ok("Zajęcia dodane.");
    }

    @GetMapping("/api/users/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/api/users/mypage")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> getMyDetails() {
        return ResponseEntity.ok(userRepository.findUserByMail(getLoggedIn().getUsername()));
    }

    @GetMapping("/api/users/myevents")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public List<Event> showMyEvents(){
        Optional<User> u1 = userRepository.findUserByMail(getLoggedIn().getUsername());
        if(u1.isPresent()){
            return userService.showMyEvents(u1);
        }
        return null;
    }

    @GetMapping("/api/checkweather")
    public void checkWeather(){

    }

    @PostMapping("api/user/myevents/cancell")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public void resignEvent(@RequestParam Long id){
        eventService.resign(id);
    }

    @PostMapping("api/user/myevents/discount")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public void discountEvent(@RequestParam Long id){
        eventService.discount(id);
    }


    public UserDetails getLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}