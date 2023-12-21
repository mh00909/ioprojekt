package com.ioproject.reservetheweather.repository;


import com.ioproject.reservetheweather.model.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class EventRepositoryTest {
    private EventRepository eventRepository;

    @Autowired
    public EventRepositoryTest(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    @Test
    public void EventRepositorySaveTest(){
        LocalDate date = LocalDate.parse("2025-12-09");
        LocalTime time = LocalTime.parse("19:00");
        Event event = Event.builder().date(date).time(time).build();
        eventRepository.save(event);
        assertThat(event).isNotNull();
        assertTrue(event.getDate().isEqual(date));
        assertTrue(event.getTime().equals(time));
    }
}
