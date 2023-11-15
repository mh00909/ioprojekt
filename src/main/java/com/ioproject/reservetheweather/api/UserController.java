package com.ioproject.reservetheweather.api;
import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import com.ioproject.reservetheweather.service.EventService;
import com.ioproject.reservetheweather.service.UserService;
import com.ioproject.reservetheweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@CrossOrigin(allowCredentials = "true")
@RestController
@RequestMapping
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;
    private final UserService userService;
    private final EventService eventService;
    private final WeatherService weatherService;

    @Autowired
    public UserController(UserService userService, EventService eventService, WeatherService weatherService) {
        this.userService = userService;
        this.weatherService = weatherService;
        this.eventService = eventService;
    }

    @GetMapping("/Glowna")
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



    @GetMapping("/api/events/myevents")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> showMyEvents(){
        Optional<User> user = userRepository.findUserByMail(getLoggedIn().getUsername());
        if(user.isPresent()) {
            return ResponseEntity.ok().body(userService.showMyEvents(user.get().getId()));
        }
        return null;
    }



    @GetMapping("/api/users/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/Konto")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public ResponseEntity<Object> getMyDetails() {
        return ResponseEntity.ok(userRepository.findUserByMail(getLoggedIn().getUsername()));
    }

    @GetMapping("/api/user/update/name")
    public ResponseEntity<Object> updateName(@RequestParam String newName){
        if(userService.updateUserName(userRepository.findUserByMail(getLoggedIn().getUsername()).get().getId(), newName))
            return ResponseEntity.ok("Poprawnie zmieniono nazwę użytkownika.");
        return ResponseEntity.status(404).body("Nie udało się zmienić nazwy użytkownika.");
    }
    @GetMapping("/api/user/update/email")
    public ResponseEntity<Object> updateMail(@RequestParam String newMail){
        if(userService.updateUserMail(userRepository.findUserByMail(getLoggedIn().getUsername()).get().getId(), newMail))
            return ResponseEntity.ok("Udało się zmienić e-mail.");
        return ResponseEntity.status(404).body("Nie udało się zmienić adresu e-mail.");
    }


    @GetMapping("/api/kontakt")
    public ResponseEntity<Object> kontakt(){
        String daneKontaktowe = "W przypadku problemów skontaktuj się z nami:\n e-mail: reservetheweather@gmail.com";
        return ResponseEntity.ok(daneKontaktowe);
    }

    public UserDetails getLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}