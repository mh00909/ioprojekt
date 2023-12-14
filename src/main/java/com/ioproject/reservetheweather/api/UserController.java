package com.ioproject.reservetheweather.api;
import com.ioproject.reservetheweather.model.Event;

import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import com.ioproject.reservetheweather.service.EventService;
import com.ioproject.reservetheweather.service.UserService;
import com.ioproject.reservetheweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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



    @GetMapping("/Account")
    public ResponseEntity<Object> getMyDetails() {
        return ResponseEntity.ok(userRepository.findUserByMail(getCurrentUserDetails().getUsername()));
    }
    @GetMapping("/myevents")
    public ResponseEntity<Object> showMyEvents(){
        Optional<User> user = userRepository.findUserByMail(getCurrentUserDetails().getUsername());
        if(user.isPresent()) {
            List<Event> events = userRepository.findEventsByUserId(user.get().getId());
            return ResponseEntity.ok(events);
           // return ResponseEntity.ok().body(userService.showMyEvents(user.get().getId()));
        }
        return null;
    }

    @GetMapping("/{userId}/events")
    public ResponseEntity<List<Event>> getUserEvents(@PathVariable Long userId) {
        List<Event> events = userRepository.findEventsByUserId(userId);
        return ResponseEntity.ok(events);
    }







    @GetMapping("/myEventsOnDay")
    public ResponseEntity<Object> myEventsOnDay(@RequestParam LocalDate date, @RequestParam String name){
        Optional<User> userOpt = userRepository.findUserByName(name);
        if(!userOpt.isPresent()){
            return ResponseEntity.badRequest().body("Użytkownik niezalogowany.");
        }
        User user = userOpt.get();
        List<Event> eventsOnDate = new ArrayList<>();
        List<Event> events = user.getMyEvents();

        for(Event e: events){
            if(e.getDate().isEqual(date)){
                eventsOnDate.add(e);
            }
        }

        return ResponseEntity.ok(eventsOnDate);
    }
    @GetMapping("/allEventsOnDay")
    public ResponseEntity<Object> allEventsOnDay(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam String name){
        Optional<User> userOpt = userRepository.findUserByName(name);
        if(!userOpt.isPresent()){
       //     return ResponseEntity.badRequest().body("Użytkownik niezalogowany.");
        }
        List<Event> events = eventService.getEvents();
        List<Event> eventsOnDate = new ArrayList<>();
        for(Event e: events){
            if(e.getDate().isEqual(date)){
               // EventDto eventDto = eventService.convertToDto(e);
                eventsOnDate.add(e);
            }
        }
        return ResponseEntity.ok(eventsOnDate);
    }



    @PostMapping("/events/signup")
    public ResponseEntity<Object> signUpForEvent(@RequestParam Long eventid, @RequestParam String name) {
        Optional<User> user = userRepository.findUserByName(name);
        if (user.isPresent()) {
            int result = userService.joinEvent(eventid, user.get());
            if(result == 0){
                return ResponseEntity.ok("Nie odnaleziono wybranych zajęć.");
            }
            else if(result == 1){
                return ResponseEntity.ok("Użytkownik jest już zapisany na te zajęcia.");
            }
            else if(result == 2){
                return ResponseEntity.ok("Użytkownik ma inne zajęcia w tym czasie.");
            }
            else {
                int eventResponse = eventService.addPerson(eventid, user.get());
                if(eventResponse==3){
                    return ResponseEntity.ok("Na podane zajęcia zapisała się już maksymalna liczba uczestników.");
                }
                userRepository.save(user.get());
                eventRepository.save(eventRepository.findById(eventid).get());
                return ResponseEntity.ok("Zapisano poprawnie.");
            }

        }
        return ResponseEntity.status(404).body("Nie udało się zapisać. Spróbuj ponownie");
    }

    @PostMapping("/myevents/cancell")
    public ResponseEntity<Object> resignEvent(@RequestParam Long id) {
        eventService.resign(id, userRepository.findUserByMail(getCurrentUserDetails().getUsername()));
        userService.resign(id, userRepository.findUserByMail(getCurrentUserDetails().getUsername()));
        return ResponseEntity.ok("Zrezygnowano z zajęć.");
    }

    @PostMapping("/myevents/discount")
    public ResponseEntity<Object> discountEvent(@RequestParam Long id) {
        if (eventService.discount(id, userRepository.findUserByMail(getCurrentUserDetails().getUsername()))) {
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