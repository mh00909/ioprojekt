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

/**
 * Kontroler REST API dla użytkowników.
 * Umożliwia zarządzanie własnymi danymi oraz udziałem w wydarzeniach.
 */
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

    /**
     * Konstruktor dodaje zależne serwisy.
     * @param userService
     * @param eventService
     * @param weatherService
     */
    @Autowired
    public UserController(UserService userService, EventService eventService, WeatherService weatherService) {
        this.userService = userService;
        this.weatherService = weatherService;
        this.eventService = eventService;
    }


    /**
     * Pobiera szczegóły zalogowanego użytkownika.
     * @return ResponseEntity z danymi użytkownika.
     */
    @GetMapping("/Account")
    public ResponseEntity<Object> getMyDetails() {
        return ResponseEntity.ok(userRepository.findUserByMail(getCurrentUserDetails().getUsername()));
    }
    /**
     * Wyświetla zajęcia, na które jest zapisany dany użytkownik.
     * @return ResponseEntity z listą zajęć.
     */
    @GetMapping("/myevents")
    public ResponseEntity<Object> showMyEvents(){
        Optional<User> user = userRepository.findUserByMail(getCurrentUserDetails().getUsername());
        if(user.isPresent()) {
            List<Event> events = userRepository.findEventsByUserId(user.get().getId());
            return ResponseEntity.ok(events);

        }
        return null;
    }


    /**
     * Wyświetla zajęcia, na które jest zapisany dany użytkownik, które mają się odbyć we wskazanym dniu.
     * @param date Data wydarzeń do wyświetlenia.
     * @param name Login użytkownika.
     * @return ResponseEntity z listą zajęć na dany dzień.
     */

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


    /**
     * Wyświetla wszystkie zajęcia, które mają się odbyć we wskazanym dniu.
     * @param date Data zajęć do wyświetlenia.
     * @param name Login zalogowanego użytkownika.
     * @return ResponseEntity z listą wszystkich zajęć na dany dzień.
     */
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

    /**
     * Zapisuje użytkownika na zajęcia.
     * @param eventid Identyfikator zajęć.
     * @param name login użytkownika.
     * @return ResponseEntity z informacją o zapisaniu na zajęcia.
     */
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


    /**
     * Pozwala użytkownikowi zrezygnować z udziału w zajęciach.
     * @param id Identyfikator zajęć.
     * @return ResponseEntity z informacją o rezygnacji z zajęć.
     */
    @PostMapping("/myevents/cancell")
    public ResponseEntity<Object> resignEvent(@RequestParam Long id) {
        eventService.resign(id, userRepository.findUserByMail(getCurrentUserDetails().getUsername()));
        userService.resign(id, userRepository.findUserByMail(getCurrentUserDetails().getUsername()));
        return ResponseEntity.ok("Zrezygnowano z zajęć.");
    }


    /**
     * Przyznaje zniżkę dla danych zajęć.
     * @param id Identyfikator zajęć.
     * @return ResponseEntity z informacją o przyznanej zniżce.
     */
    @PostMapping("/myevents/discount")
    public ResponseEntity<Object> discountEvent(@RequestParam Long id) {
        if (eventService.discount(id, userRepository.findUserByMail(getCurrentUserDetails().getUsername()))) {
            return ResponseEntity.ok("Przyznano zniżkę.");
        }
        return ResponseEntity.ok("Nie przyznano zniżki. Spróbuj ponownie.");
    }


    /**
     * Zmienia login użytkownika.
     * @param newName nowy login
     * @return ResponseEntity z informacją o zmianie loginu użytkownika.
     */
    @GetMapping("/updateName")
    public ResponseEntity<Object> updateName(@RequestParam String newName) {
        Optional<User> userOpt = userRepository.findUserByMail(getCurrentUserDetails().getUsername());
        if (userOpt.isPresent()) {
            if (userService.updateUserName(userOpt.get().getId(), newName)) {
                return ResponseEntity.ok("Poprawnie zmieniono nazwę użytkownika.");
            } else {
                return ResponseEntity.status(404).body("Nie udało się zmienić nazwy użytkownika.");
            }
        } else {
            return ResponseEntity.status(404).body("Nie znaleziono użytkownika.");
        }
    }

    /**
     * Zmienia adres e-mail użytkownika.
     * @param newMail Nowy adres e-mail.
     * @return ResponseEntity z informacją o zmianie adresu e-mail.
     */
    @GetMapping("/updateEmail")
    public ResponseEntity<Object> updateMail(@RequestParam String newMail) {
        Optional<User> userOpt = userRepository.findUserByMail(getCurrentUserDetails().getUsername());
        if (userOpt.isPresent()) {
            if (userService.updateUserMail(userOpt.get().getId(), newMail)) {
                return ResponseEntity.ok("Udało się zmienić e-mail.");
            } else {
                return ResponseEntity.status(404).body("Nie udało się zmienić adresu e-mail.");
            }
        } else {
            return ResponseEntity.status(404).body("Nie znaleziono użytkownika.");
        }
    }


    /**
     * Pobiera dane bieżącego użytkownika.
     * @return Szczegóły bieżącego użytkownika.
     */

    public UserDetails getCurrentUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal);
        }
        return null;
    }
}