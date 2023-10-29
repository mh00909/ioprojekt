package com.ioproject.reservetheweather.service;

import com.ioproject.reservetheweather.entity.Event;
//import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.entity.User;
import com.ioproject.reservetheweather.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public void checkWeather(Event event){
        // TODO:
        // porównanie aktualnej pogody z dopuszczalną pogodą
        // jeśli jest zła: zmiana event.badWeather=true
    }

    public void addEvent(Event event) {
        eventRepository.save(event);
    }

    public void resign(Long id) {
        // TODO:
        // sprawdź czy pogoda jest zła
        // usuń użytkownika z zapisanych użytkowników i event u użytkownika
    }

    public void discount(Long id) {
        // TODO:
    }

    public void addPerson(Long eventid, User user) {
        Optional<Event> event = eventRepository.findById(eventid);
        if(event.isPresent()){
            event.get().getUsers().add(user);
        }
    }
}
