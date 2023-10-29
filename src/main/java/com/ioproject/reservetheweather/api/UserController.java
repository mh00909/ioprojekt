package com.ioproject.reservetheweather.api;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @GetMapping("/")
    public String hello() {
        return "Strona domowa";
    }

    @PostMapping("/user/save")
    public ResponseEntity<Object> saveUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User result = userRepository.save(user);
        if (result.getId() > 0) {
            return ResponseEntity.ok("User was saved.");
        }
        return ResponseEntity.status(404).body("Error. User not saved.");
    }

    @GetMapping("/events/all")
    public ResponseEntity<Object> getAllEvents() {
        return ResponseEntity.ok(eventRepository.findAll());
    }

    @GetMapping("/users/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/users/single")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> getMyDetails() {
        return ResponseEntity.ok(userRepository.findUserByMail(getLoggedIn().getUsername()));
    }

    public UserDetails getLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}