package com.ioproject.reservetheweather.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
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
public class User implements UserDetails {
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



    @ManyToMany(mappedBy = "users")
    @JsonIgnore
    private List<Event> myEvents;



    @Column
    private boolean accountExpired = false;
    @Column private boolean accountLocked = false;
    @Column private boolean credentialsExpired = false;
    @Column private boolean accountDisabled = false;
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

    @Transactional
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


    public UserDetails getCurrentUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal);
        }
        return null;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(roles));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return !accountDisabled;
    }

}



