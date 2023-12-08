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

    @GetMapping("/api/events/all")
    public ResponseEntity<Object> getAllEvents() {
        return ResponseEntity.ok(eventRepository.findAll());
    }

    @GetMapping("/api/events/description/{eventid}")
    public ResponseEntity<Object> showEventDescription(@RequestParam Long eventid){
        Optional<Event> event = eventRepository.findById(eventid);
        if(event.isPresent()){
            return ResponseEntity.ok(eventService.showDescription(event.get()));
        }
        return ResponseEntity.status(404).body("Nie znaleziono strony.");
    }

    @PostMapping("/api/events/signup/{eventid}")
    public ResponseEntity<Object> signUpForEvent(@RequestParam Long eventid){
     /*  Optional<User> user = userRepository.findUserByMail(getLoggedIn().getUsername());
        if(user.isPresent()) {
            eventService.addPerson(eventid, user.get());
            userService.joinEvent(eventid, user.get());
            return ResponseEntity.ok("Zapisano poprawnie.");
        } */
        return ResponseEntity.status(404).body("Nie udało się zapisać. Spróbuj ponownie");
    }


    @RequestMapping(value = "/addEvent", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Object> addEvent(@ModelAttribute Event event){
        eventService.addEvent(event);
        return ResponseEntity.ok("Dodano wydarzenie");
    }

    @RequestMapping(value = "/api/removeEvent", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<Object> removeEvent(@RequestParam Event event){
        boolean removed = eventService.removeEvent(event.getId());
        if(removed){
            return ResponseEntity.ok("Zrezygnowano z zajęć.");
        }
        return ResponseEntity.badRequest().body("Brak zajęć o podanym ID.");
    }







    ///////////////////////////////////////////////////////// do sprawdzenia




    @PostMapping("/api/user/myevents/cancell/{id}")
    public ResponseEntity<Object> resignEvent(@RequestParam Long id){
       /* eventService.resign(id, userRepository.findUserByMail(getLoggedIn().getUsername()));
        userService.resign(id, userRepository.findUserByMail(getLoggedIn().getUsername())); */
        return ResponseEntity.ok("Zrezygnowano z zajęć.");
    }

    @PostMapping("/api/user/myevents/discount")
    public ResponseEntity<Object> discountEvent(@RequestParam Long id){
     /*   if( eventService.discount(id, userRepository.findUserByMail(getLoggedIn().getUsername()))){
            return ResponseEntity.ok("Przyznano zniżkę.");
        } */
        return ResponseEntity.ok("Nie przyznano zniżki. Spróbuj ponownie.");
    }

    @PostMapping("/api/user/myevents/reschedule")
    public ResponseEntity<Object> rescheduleEvent(@RequestParam Long eventId, @RequestParam String date1) {

     /*   if(eventService.reschedule(eventId, userRepository.findUserByMail(getLoggedIn().getUsername()), date1)){
            return ResponseEntity.ok("Zapisano na zajęcia w innym terminie.");
        }
    */
        return ResponseEntity.ok("Nie zmieniono terminu zajęć. Spróbuj ponownie.");


    }


/*

    public UserDetails getLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

 */



}
