package com.ioproject.reservetheweather.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Klasa reprezentująca zajęcia i przechowująca o nich informacje.
 * Umożliwia zapisywanie się przez użytkowników na zajęcia.
 */

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="events")
public class Event {
    /**
     * unikalny identyfikator zajęć
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * nazwa zajęć
     */
    @Column
    private String name;
    /**
     * lokalizacja zajęć
     */
    @Column
    private String location;
    /**
     * cena uczestnictwa w zajęciach
     */
    @Column
    private double price;
    /**
     * czas rozpoczęcia zajęć
     */
    @Column
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime time;
    /**
     * data odbycia się zajęć
     */
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    /**
     * czas trwania zajęć w godzinach
     */
    @Column
    private double duration;
    /**
     * opis zajęć
     */
    @Column
    private String description;
    /**
     * maksymalna liczba zapisanych użytkowników
     */
    @Column
    private int maxUsers;
    /**
     * liczba zapisanych użytkowników
     */
    @Column
    public int signedUsers;
    /**
     * lista zapisanych użytkowników
     */
    @ManyToMany
    public List<User> users;
    /**
     * minimalna dopuszczalna temperatura
     */
    @Column
    double minTemperature;
    /**
     * maksymalna dopuszczalna temperatura
     */
    @Column
    double maxTemperature;
    /**
     * Flaga wskazująca, czy przewidywana jest zła pogoda w czasie trwania zajęć.
     */
    public boolean badWeather = false;
    /**
     * Flaga wskazująca, czy zapisanych użytkowników obowiązuje zniżka.
     */
    public boolean discount = false;

    /**
     * Konstruktor
     * @param name - nazwa zajęć
     * @param dateString - data zajęć
     * @param timeString - godzina
     * @param duration - czas trwania w godzinach
     * @param maxUsers - maksymalna dopuszczalna liczba uczestników
     */
    public Event(String name, String dateString, String timeString, double duration, int maxUsers){
        this.name=name;
        this.date = LocalDate.parse(dateString);
        this.time = LocalTime.parse(timeString);
        this.duration = duration;
        this.signedUsers=0;
        this.maxUsers=maxUsers;
        description="";
    }


    /**
     *
     * @param eventDate - data zajęć
     * @param eventTime - godzina
     * @param eventDuration - czas trwania w godzinach
     * @param eventDescription - opis zajęć
     * @param eventName - nazwa zajęć
     * @param maxUsers - maksymalna dopuszczalna liczba uczestników
     * @param eventLocation - lokalizacja
     * @param eventPrice - cena uczestnictwa
     * @param eventMinTemperature - minimalna dopuszczalna temperatura
     * @param eventMaxTemperature - maksymalna dopuszczalna temperatura
     */
    public Event(LocalDate eventDate, LocalTime eventTime, double eventDuration,
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


    /**
     * Zwiększa liczbę zapisanych uczestników.
     * Dodaje użytkownika do listy zapisanych uczestników.
     * @param user - zapisujący się użytkownik
     * @return czy udało się dodać
     */
    public boolean addNewUser(User user){
        if(signedUsers==maxUsers){
            return false;
        }
        users.add(user);
        signedUsers+=1;
        return true;
    }

    /**
     * Usuwa użytkownika z listy zapisanych uczestników.
     * @param user
     */
    public void removeUser(User user){
        users.remove(user);
    }
}

