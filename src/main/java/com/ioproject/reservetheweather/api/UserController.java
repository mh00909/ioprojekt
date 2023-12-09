package com.ioproject.reservetheweather.api;
import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import com.ioproject.reservetheweather.service.EventService;
import com.ioproject.reservetheweather.service.UserService;
import com.ioproject.reservetheweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
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


/*
    @RequestMapping(value = "/Rejestracja", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<?> saveUser(@ModelAttribute User user) {
        if(userService.addNewUser(user) == 2){
            return ResponseEntity.ok().body("Udało się");
        } else {
            if(userService.addNewUser(user) == 0){
                return ResponseEntity.ok()
                        .body("Błąd: podany E-mail już zajęty");
            }

            else if(userService.addNewUser(user) == 1){
                return ResponseEntity.ok()
                        .body("Błąd: podany login już zajęty");
            }
        }
        return ResponseEntity.ok().body("Inny błąd");
    }


 */


    @GetMapping("/Account")
    public ResponseEntity<Object> getMyDetails() {
        return ResponseEntity.ok(userRepository.findUserByMail(getCurrentUserDetails().getUsername()));
    }
    @GetMapping("/myevents")
    public ResponseEntity<Object> showMyEvents(){
        Optional<User> user = userRepository.findUserByMail(getCurrentUserDetails().getUsername());
        if(user.isPresent()) {
            return ResponseEntity.ok().body(userService.showMyEvents(user.get().getId()));
        }
        return null;
    }


    @PostMapping("/events/signup/{eventid}")
    public ResponseEntity<Object> signUpForEvent(@RequestParam Long eventid){
        Optional<User> user = userRepository.findUserByMail(getCurrentUserDetails().getUsername());
        if(user.isPresent()) {
            eventService.addPerson(eventid, user.get());
            userService.joinEvent(eventid, user.get());
            return ResponseEntity.ok("Zapisano poprawnie.");
        }
        return ResponseEntity.status(404).body("Nie udało się zapisać. Spróbuj ponownie");
    }

    @PostMapping("/myevents/cancell/{id}")
    public ResponseEntity<Object> resignEvent(@RequestParam Long id){
        eventService.resign(id, userRepository.findUserByMail(getCurrentUserDetails().getUsername()));
        userService.resign(id, userRepository.findUserByMail(getCurrentUserDetails().getUsername()));
        return ResponseEntity.ok("Zrezygnowano z zajęć.");
    }
    @PostMapping("/myevents/discount")
    public ResponseEntity<Object> discountEvent(@RequestParam Long id){
        if( eventService.discount(id, userRepository.findUserByMail(getCurrentUserDetails().getUsername()))){
            return ResponseEntity.ok("Przyznano zniżkę.");
        }
        return ResponseEntity.ok("Nie przyznano zniżki. Spróbuj ponownie.");
    }





    @GetMapping("/updateName")
    public ResponseEntity<Object> updateName(@RequestParam String newName){
        if(userService.updateUserName(userRepository.findUserByMail(getCurrentUserDetails().getUsername()).get().getId(), newName))
            return ResponseEntity.ok("Poprawnie zmieniono nazwę użytkownika.");
        return ResponseEntity.status(404).body("Nie udało się zmienić nazwy użytkownika.");
    }
    @GetMapping("/updateEmail")
    public ResponseEntity<Object> updateMail(@RequestParam String newMail){
        if(userService.updateUserMail(userRepository.findUserByMail(getCurrentUserDetails().getUsername()).get().getId(), newMail))
            return ResponseEntity.ok("Udało się zmienić e-mail.");
        return ResponseEntity.status(404).body("Nie udało się zmienić adresu e-mail.");
    }



    public UserDetails getCurrentUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal);
        }
        return null;
    }
}