package com.ioproject.reservetheweather.model;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Klasa reprezentująca użytkownika zapisującego się na zajęcia
 */
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String mail;
    @Column(name = "phone number")
    private long phoneNumber;
    @Column()
    private String password;
    @Column
    private String roles;

    @ManyToMany
    private List<Event> myEvents;

    /**
     * Konstruktor, w którym ręcznie wpisujemy id użytkownika
     * @param id
     * @param name
     * @param mail
     * @param password
     * @param phoneNumber
     * @param roles
     */
    public User(Long id, String name, String mail, String password, long phoneNumber, String roles) {
        this.id = id;
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    /**
     * Konstruktor używany przy rejestracji użytkownika. Id oraz rola wyznaczane automatycznie.
     * @param name
     * @param mail
     * @param password
     * @param phoneNumber
     */
    public User(String name, String mail, String password, long phoneNumber) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.roles = "USER";
    }


    /**
     *
     * @return dane użytkownika w formacie String
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", e-mail='" + mail + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }


    /**
     * Dodaje zajęcia do listy zajęć danego użytkownika, na które jest zapisany
     * @param event
     * @return false, gdy jest już zapisany na dane zajęcia, true - w przeciwnym przypadku
     */
    public boolean joinEvent(Event event){
        if(myEvents.contains(event)){
            return false;
        }
        myEvents.add(event);
        return true;
    }

    /**
     * Usuwa dane zajęcia, z listy zajęć, na które jest zapisany dany użytkownik
     * @param event
     * @return false, gdy nie był zapisany wcześniej na dane zajęcia, true - gdy udało się je usunąć z listy
     */
    public boolean resign(Event event){
        if(myEvents.contains(event)){
            myEvents.remove(event);
            return true;
        }
        return false;
    }
}



