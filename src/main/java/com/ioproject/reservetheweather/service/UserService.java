package com.ioproject.reservetheweather.service;
import com.ioproject.reservetheweather.model.Event;
import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.repository.EventRepository;
import com.ioproject.reservetheweather.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;


/**
 * Klasa służąca do zarządzania użytkownikami.
 * Oferuje funkcjonalności związane z tworzeniem, usuwaniem i aktualizacją danych.
 * Umożliwia również operacje na wydarzeniach powiązanych z użytkownikiem.
 *
 */
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Pobiera listę wszystkich użytkowników
     * @return Lista użytkowników
     */
   public List<User> getUsers(){
        return userRepository.findAll();
    }


    /**
     * Dodaje nowego użytkownika.
     * @param user Obiekt użytkownika do dodania
     * @return Kod statusu operacji (0 - istnieje e-mail, 1 - istnieje nazwa, 2 - sukces)
     */
    @Transactional
    public int addNewUser(User user) {
        Optional<User> exists = userRepository.findUserByMail(user.getMail());
        Optional<User> exists2 = userRepository.findUserByName(user.getName());
        if(exists.isPresent()){
            return 0;
        }
        if(exists2.isPresent()){
            return 1;
        }
        userRepository.save(user);
        return 2;
    }

    /**
     * Usuwa użytkownika na podstawie jego ID
     * @param login użytkownika do usunięcia
     * @return true, jeśli operacja się powiedzie
     * @throws IllegalStateException, jeśli użytkownik nie istnieje
     */
    public boolean deleteUser(String login) {
        userRepository.findUserByName(login);
        Optional<User> userOpt = userRepository.findUserByName(login);
        if(!userOpt.isPresent()){
            throw new IllegalStateException("Użytkownik nie istnieje!");
        }
        userRepository.delete(userOpt.get());
        return true;
    }

    /**
     * Aktualizuje adres e-mail użytkownika.
     *
     * @param userID ID użytkownika
     * @param email Nowy adres e-mail
     * @return true, jeśli aktualizacja się powiedzie
     * @throws IllegalStateException jeśli użytkownik nie istnieje lub e-mail jest zajęty
     */
    @Transactional
    public boolean updateUserMail(Long userID, String email) {
        User user = userRepository.findById(userID)
                .orElseThrow( ()->new IllegalStateException("Użytkownik z podanym ID nie istnieje.") );

        if(email!=null && email.length()>0){
            Optional<User> userOptional = userRepository.findUserByMail(email);
            if(userOptional.isPresent()){
                throw new IllegalStateException("Podany e-mail jest zajęty.");
            }
            user.setMail(email);
            return true;
        }
        return false;
    }


    /**
     * Aktualizuje nazwę użytkownika.
     *
     * @param userID ID użytkownika
     * @param name Nowa nazwa użytkownika
     * @return true, jeśli aktualizacja się powiedzie
     * @throws IllegalStateException jeśli użytkownik nie istnieje lub e-mail jest zajęty
     */
    public boolean updateUserName(Long userID, String name){
        User user = userRepository.findById(userID)
                .orElseThrow( ()->new IllegalStateException("Użytkownik z podanym ID nie istnieje.") );
        if(name!=null && name.length()>0){
            Optional<User> userOptional = userRepository.findUserByName(name);
            if(userOptional.isPresent()){
                throw new IllegalStateException("Podany login jest zajęty.");
            }
            user.setName(name);
            return true;
        }
        return false;
    }



    /**
     * Wyświetla zajęcia, w których uczestniczy dany użytkownik.
     *
     * @param userID ID użytkownika
     * @return Lista zajęć użytkownika
     * @throws IllegalStateException jeśli użytkownik nie istnieje
     */

    public List<Event> showMyEvents(Long userID) {
        User user = userRepository.findById(userID)
                .orElseThrow( ()->new IllegalStateException("Użytkownik z podanym ID nie istnieje.") );
        return user.getMyEvents();
    }



    /**
     * Pozwala użytkownikowi zapisać się na zajęcia.
     * @param eventid ID zajęć
     * @param user Użytkownik zapisujący się na zajęcia
     * @return 3 - jeśli dołączenie się powiedzie,
     * 1 - jeśli użytkownik jest już zapisany na te zajęcia,
     * 2 - jeśli użytkownik ma inne zajęcia w tym czasie
     * 3 - jeśli nie znaleziono podanych zajęć
     */

    @Transactional
    public int joinEvent(Long eventid, User user){
        Optional<Event> eventOpt = eventRepository.findById(eventid);
        if(eventOpt.isPresent()){
            Event newEvent = eventOpt.get();
            if(user.getMyEvents().contains(newEvent)){
                return 1; // użytkownik jest już zapisany na te zajęcia
            }
            LocalTime newEventEndTime = newEvent.getTime().plusHours(newEvent.getDuration());
            for(Event e : user.getMyEvents()){
                if(e.getDate().isEqual(newEvent.getDate())){ // zajęcia w tym samym dniu
                    LocalTime joinedEndTime = e.getTime().plusHours(e.getDuration());

                    if(!e.getTime().isAfter(newEventEndTime) && !newEvent.getTime().isAfter(joinedEndTime)){
                        return 2; // użytkownik ma inne zajęcia w tym czasie
                    }
                }
            }
            user.joinEvent(newEvent);
            return 3; // udało się zapisać
        }
        return 0; // nie znaleziono zajęć
    }


    /**
     * Pozwala użytkownikowi zrezygnować z udziału w zajęciach.
     *
     * @param eventID ID zajęć
     * @param user Użytkownik rezygnujący z zajęć
     * @return true, jeśli rezygnacja się powiedzie
     */
    public boolean resign(Long eventID, Optional<User> user){
        Optional<Event> event = eventRepository.findById(eventID);
        if(event.isPresent()){
            user.get().resign(event.get());
            return true;
        }
        return false;
    }


}

