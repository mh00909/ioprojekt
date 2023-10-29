package com.ioproject.reservetheweather.service;

import com.ioproject.reservetheweather.entity.Event;
//import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public void addEvent(Event event) {
        eventRepository.save(event);
    }
}
