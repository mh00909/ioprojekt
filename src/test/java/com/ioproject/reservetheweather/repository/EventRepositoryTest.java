package com.ioproject.reservetheweather.repository;


import com.ioproject.reservetheweather.entity.Event;
import com.sun.source.tree.AssertTree;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

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
    public void EventRepositorySaveWithDate(){
        LocalDateTime d = LocalDateTime.parse("2023-12-12 12:12", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Event event = Event.builder().time(d).build();
        eventRepository.save(event);

        assertThat(event).isNotNull();
        assertTrue(event.getTime().isEqual(d));

    }
}
