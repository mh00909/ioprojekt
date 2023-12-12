package com.ioproject.reservetheweather.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

    @Column
    private String location;

    @Column
    private double price;

    @Column
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column
    private int duration;
    @Column
    private String description;
    @Column
    private int maxUsers;
    @Column
    public int signedUsers;
    @ManyToMany
    public List<User> users;
    @Column
    double minTemperature;
    @Column
    double maxTemperature;

    public boolean badWeather = false;
    public boolean discount = false;

    //boolean status;


    public Event(String name, String dateString, String timeString, int duration, int maxUsers){
        this.name=name;
        this.date = LocalDate.parse(dateString);
        this.time = LocalTime.parse(timeString);
        this.duration = duration;
        this.signedUsers=0;
        this.maxUsers=maxUsers;
        description="";
    }



    // przy dodawaniu przez admina
    public Event(LocalDate eventDate, LocalTime eventTime, int eventDuration,
                 String eventDescription, String eventName,
                 int maxUsers, String eventLocation, double eventPrice,
                 double eventMinTemperature, double eventMaxTemperature){
        date=eventDate;
        time=eventTime;

        duration = eventDuration;
        description=eventDescription;
        this.maxUsers = maxUsers;
        this.signedUsers=0;
        name=eventName;
        location = eventLocation;
        price = eventPrice;
        minTemperature = eventMinTemperature;
        maxTemperature = eventMaxTemperature;
        discount=false;
    }




    public void removeUser(User user){
        users.remove(user);
    }
}

