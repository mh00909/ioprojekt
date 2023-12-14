package com.ioproject.reservetheweather.service;

import com.ioproject.reservetheweather.model.Event;
//import com.ioproject.reservetheweather.repository.EventRepository;

import com.ioproject.reservetheweather.model.User;
import com.ioproject.reservetheweather.model.WeatherData;
import com.ioproject.reservetheweather.repository.EventRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    private final WeatherService weatherService;
    public EventService(EventRepository eventRepository, WeatherService weatherService) {
        this.eventRepository = eventRepository;
        this.weatherService = weatherService;
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public boolean checkWeather(Long eventID){

        Optional<Event> event = eventRepository.findById(eventID);
        if(event.isEmpty()) return false;
        WeatherData wd = new WeatherData();
        wd =weatherService.checkWeather();
        double temp = wd.getMain().getTemp();
        if(temp > event.get().getMaxTemperature() || temp < event.get().getMinTemperature()){
            event.get().setBadWeather(true);
        }
        return true;
    }

    public boolean addEvent(Event event) {
        eventRepository.save(event);
        return true;
    }


    public boolean resign(Long eventID, Optional<User> user) {
    // usuwa użytkownika z uczestników tego wydarzenia
        if(user.isPresent()){
            Optional<Event> event = eventRepository.findById(eventID);
            if(event.isPresent()){
                LocalDate now = LocalDate.now();
                LocalDate eventDate = event.get().getDate();
                long hours = ChronoUnit.HOURS.between(now, eventDate);
                if(hours < 24){
                    // obsłuży usuwanie zajęć z listy zajęć, na które jest zapisany użytkownik
                    user.get().resign(event.get());
                    event.get().removeUser(user.get());
                    return true;
                }
            }
        }

        return false;
    }

    public boolean discount(Long eventID, Optional<User> user) {
        if(user.isPresent()){
            Optional<Event> event = eventRepository.findById(eventID);
            if(event.isPresent()){
                LocalDate now = LocalDate.now();
                LocalDate eventDate = event.get().getDate();
                long hours = ChronoUnit.HOURS.between(now, eventDate);
                if(hours < 24){

                    if(!event.get().discount){
                        // zniżka o 30%
                        event.get().setPrice(event.get().getPrice() * 0.7);
                        event.get().discount = true;
                        return true;
                    }
                    else{
                        // zniżka została już przyznana
                        return false;
                    }

                }
            }
        }

        return false;
    }

    /**
     * Dodaje użytkownika do listy uczestników danych zajęć
     * @param eventid
     * @param user
     * @return 1 jeśli udało się dodać,
     * 2 - jeśli nie znaleziono podanych zajęć
     * 3 - jeśli na dane zajęcia zapisała się już maksymalna liczba osób
     */
    public int addPerson(Long eventid, User user) {
        Optional<Event> eventOpt = eventRepository.findById(eventid);
        if(eventOpt.isPresent()){
            Event event = eventOpt.get();
            if(event.getUsers().size() < event.getMaxUsers()){
                event.addNewUser(user);
                return 1;
            }
            return 3;
        }

        return 2;
    }

    public String showDescription(Event event){
        String response = event.getDescription()
        + "Najlepsza temperatura do odbycia zajęć: od "
                + event.getMinTemperature() + "do "+ event.getMaxTemperature()
        +"Liczba miejsc: "+ event.getMaxUsers() + ", liczba zapisanych uczestników: "+event.getSignedUsers();
        return response;
    }

    public boolean reschedule(Long eventID, Optional<User> user, String date1) {
        // takie samo wydarzenie na które był zapisany ale z inną datą

        if(user.isPresent()){
            Optional<Event> event = eventRepository.findById(eventID);
            if(event.isPresent()){
                LocalDateTime now = LocalDateTime.now();
                LocalDate eventDate = event.get().getDate();
                long hours = ChronoUnit.HOURS.between(now, eventDate);
                if(hours < 24){

                    Event newEvent = new Event();
                    newEvent.setDate(LocalDate.parse(date1));

                    user.get().resign(event.get());
                    user.get().joinEvent(newEvent);

                    return true;
                }
            }
        }

        return false;

    }

    public String badWeatherInfrom(User user, Event event){
        String message = "Na datę "+ event.getTime() + " przewidywana jest zła pogoda.\n" +
                "W związku z tym możesz poprosić o otrzymanie zniżki na zajęcia. " +
                "Możesz także zrezygnować z udziału lub zapisać się na zajęcia w innym terminie.";
        return message;
    }

    public boolean removeEvent(Long id) {

        Optional<Event> event = eventRepository.findById(id);
        if(event.isPresent()){
            eventRepository.delete(event.get());
            return true;
        }
        return false;
    }

    public boolean rescheduleEvent(Long id, LocalDate newDate, LocalTime newTime){
        Optional<Event> eventOpt = eventRepository.findById(id);
        if(eventOpt.isPresent()){
            Event event = eventOpt.get();
            event.setDate(newDate);
            event.setTime(newTime);
            return true;
        }
        return false;
    }




/*


    public EventDto convertToDto(Event event) {
        EventDto dto = new EventDto();
        dto.setId(event.getId());
        dto.setName(event.getName());
        dto.setDescription(event.getDescription());
        dto.setLocation(event.getLocation());
        dto.setDiscount(event.isDiscount());
        dto.setPrice(event.getPrice());
        dto.setTime(event.getTime());
        dto.setDate(event.getDate());
        dto.setDuration(event.getDuration());
        dto.setUsers(event.getUsers());
        dto.setSignedUsers(event.getSignedUsers());
        dto.setMaxUsers(event.getMaxUsers());
        dto.setMinTemperature(event.getMinTemperature());
        dto.setMaxTemperature(event.getMaxTemperature());
        return dto;
    }

 */
}
