package com.ioproject.reservetheweather.api;
import com.ioproject.reservetheweather.model.Event;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import com.ioproject.reservetheweather.service.EventService;
import com.ioproject.reservetheweather.service.UserService;
import com.ioproject.reservetheweather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * Kontroler REST API dla administratora serwisu.
 * Umożliwia zarządzanie użytkownikami, zajęciami, rezerwacjami.
 */
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

    /**
     * Konstruktor dodający zależne klasy serwisowe.
     * @param userService
     * @param eventService
     * @param weatherService
     */
    @Autowired
    public AdminController(UserService userService, EventService eventService, WeatherService weatherService) {
        this.userService = userService;
        this.weatherService = weatherService;
        this.eventService = eventService;
    }
    /**
     * Pobiera wszystkich użytkowników.
     * @return ResponseEntity z listą użytkowników.
     */
    @GetMapping("/allusers")
    public ResponseEntity<Object> getAllUsers()
    {
        return ResponseEntity.ok(userRepository.findAll());
    }

    /**
     * Dodaje nowe zajęcia.
     * @return ResponseEntity z informacją o dodaniu zajęć.
     */
    @PostMapping("/addEvent")
    public ResponseEntity<Object> addEvent(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                           @RequestParam("time")LocalTime eventTime,
                                           @RequestParam("duration") double duration,
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


    /**
     * Zmienia termin zajęć.
     * @return ResponseEntity z informacją o zmianie terminu.
     */
    @PostMapping("/rescheduleEvent")
    public ResponseEntity<Object> rescheduleEvent(@RequestParam("eventID") Long eventID,
                                                  @RequestParam("newTime") LocalTime eventTime,
                                                  @RequestParam("newDate") LocalDate eventDate){
        boolean zmieniono = eventService.rescheduleEvent(eventID, eventDate, eventTime);
        if(!zmieniono){
            return ResponseEntity.badRequest().body("Brak podanych zajęć.");
        }
        return ResponseEntity.ok("Zmieniono termin zajęć");
    }

    /**
     * Usuwa zajęcia.
     * @return ResponseEntity z informacją o usunięciu.
     */
    @PostMapping("/removeEvent")
    public ResponseEntity<Object> removeEvent(@RequestParam Long eventID){
        boolean removed = eventService.removeEvent(eventID);
        if(removed){
            return ResponseEntity.ok("Zrezygnowano z zajęć.");
        }
        return ResponseEntity.badRequest().body("Brak zajęć o podanym ID.");
    }



}
