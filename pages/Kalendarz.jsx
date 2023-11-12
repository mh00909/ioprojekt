import React, { useState } from "react";
import Datetime from "react-datetime";
import "react-datetime/css/react-datetime.css";
import "./Kalendarz.css";
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';

const Kalendarz = () => {
    const [selectedDate, setSelectedDate] = useState(null);
    const [selectedTime, setSelectedTime] = useState(null);


    const onDateSelect = (date) => {
        setSelectedDate(date);
    }

    const onTimeSelect = (moment) => {
        setSelectedTime(moment);
    }

    const reserveEvent = () => {
        if (selectedDate && selectedTime) {
            // Tutaj możesz dodać kod obsługujący rezerwację dla wybranej daty i godziny
            console.log("Wydarzenie zarezerwowane na: ", selectedDate, selectedTime.format("HH:mm"));
            // Przykładowy kod do obsługi rezerwacji:
            // Wyślij zapytanie do serwera, zapisz w bazie danych, etc.
        } else {
            console.log("Wybierz datę i godzinę przed rezerwacją.");
        }
    }

    return (
        <div>
            <div>
                <h3>Wybierz datę:</h3>
                <Calendar onChange={onDateSelect} value={selectedDate} />
            </div>
            <div>
                <h3>Wybierz godzinę:</h3>
                <Datetime onChange={onTimeSelect} value={selectedTime} dateFormat={false} />
            </div>
            <button onClick={reserveEvent}>Zarezerwuj wydarzenie</button>
        </div>
    );
}

export default Kalendarz;