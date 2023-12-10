import React, { useState, useEffect } from "react";
import DateSelector from './components/DateSelector';
import "./AdminPanel.css";
import api from "../api";
import AllEvents from './components/AllEvents';


const AdminPanel = () => {
  const [eventDate, setEventDate] = useState("");
  const [eventTime, setEventTime] = useState("");
  const [eventDuration, setEventDuration] = useState("");
  const [eventDescription, setEventDescription] = useState("");
  const [eventName, setEventName] = useState("");
  const [maxUsers, setMaxUsers] = useState("");
  const [eventLocation, setEventLocation] = useState("");
  const [eventPrice, setEventPrice] = useState("");
  const [eventMinTemperature, setMinTemperature] = useState("");
  const [eventMaxTemperature, setMaxTemperature] = useState("");
  const [selectedDate, setSelectedDate] = useState("");

  const apiBaseUrl = process.env.REACT_APP_API_BASE_URL || "http://localhost:8080";
  const addEventEndpoint = `${apiBaseUrl}/addEvent`;


  const allEventsEndpoint = `${apiBaseUrl}/api/events/all`;
  const [allEvents, setAllEvents] = useState([]);

  const [error, setError] = useState(""); 


  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new URLSearchParams();
    formData.append('date', eventDate)
    formData.append('time', eventTime)
    formData.append('duration', eventDuration)
    formData.append('description', eventDescription)
    formData.append('name', eventName)
    formData.append('maxUsers', maxUsers)
    formData.append('location', eventLocation)
    formData.append('price', eventPrice)
    formData.append('minTemperature', eventMinTemperature)
    formData.append('maxTemperature', eventMaxTemperature)
    console.log("Dane zajęć:", formData.toString());

    try {
      const response = await api.post('/api/admin/addEvent', formData);
      if (response.data === "Dodano zajęcia") {
        console.log('Udało się dodać zajęcia', response.data)
        window.location.href = '/AdminPanel';
      } else {
        window.location.href = '/AdminPanel';
        setError("Niepoprawne dane. Spróbuj ponownie.");
      }
    } catch (error) {
      console.error("Błąd podczas wysyłania danych:", error);
      setError("Błąd podczas dodania zajęć. Spróbuj ponownie.");
    }
  };

  useEffect(() => {
    const fetchAllEvents = async () => {
      try {
        const response = await api.get('/api/events/all');
        setAllEvents(response.data);
      } catch (error) {
        console.error('Błąd podczas pobierania danych:', error);
      }
    };

    fetchAllEvents();
  }, [selectedDate]);

  const handleDateSelection = (date) => {
    setSelectedDate(date);
  };


  const handleRemoveEvent = async (eventId) => {
    try {
        const response = await api.post('/api/admin/removeEvent', { id: eventId });
        if (response.data === "Zrezygnowano z zajęć") {
            console.log('Zrezygnowano z zajęć', response.data);
            // Tu dodam logikę do odświeżania listy zajęć po usunięciu
        } else {
            console.error('Nie udało się zrezygnować z zajęć');
            setError("Nie udało się zrezygnować z zajęć. Spróbuj ponownie.");
        }
    } catch (error) {
        console.error("Błąd podczas wysyłania danych:", error);
        setError("Błąd podczas zrezygnowania z zajęć. Spróbuj ponownie.");
    }
};
    return (
      <div className="admin-panel">
        <div className="div">
        <div className="all-events-container">
          <AllEvents allEvents={allEvents} selectedDate={selectedDate} handleRemoveEvent={handleRemoveEvent}/>
        </div>
          <p className="text-wrapper">© 2024 ReserveTheWeather. All rights reserved.</p>
          <div className="overlap">
            <div className="overlap-group">
              <img className="line" alt="Line" src="https://c.animaapp.com/t0STnSju/img/line-4.svg" />
              <img className="tlo" alt="Tlo" src="https://c.animaapp.com/t0STnSju/img/tlo.png" />
              <div className="text-wrapper-2">Widok administratora</div>
              <div className="selector-style"><DateSelector onSelectDate={handleDateSelection} /></div>
              <img className="logo" alt="Logo" src="https://c.animaapp.com/t0STnSju/img/logo.png" />
              <div className="formularz-tlo">
                <div className="overlap-7"></div>
              </div>
              <div className="text-wrapper-data">Data</div>
              <input type="date"
                     className="data-form"
                     placeholder="Wprowadź datę"
                     value={eventDate}
                     onChange={(e) => setEventDate(e.target.value)}
              />
              <div className="text-wrapper-time">Godzina</div>
              <input type="time"
                     className="time-form"
                     placeholder="Wprowadź godzinę"
                     value={eventTime}
                     onChange={(e) => setEventTime(e.target.value)}
              /> 
              <div className="text-wrapper-duration">Czas trwania</div>
              <input type="number"
                     className="duration-form" 
                     placeholder="Wprowadź czas" 
                     value={eventDuration}
                     onChange={(e) => setEventDuration(e.target.value)}
              /> 
              <div className="text-wrapper-maxusers">Miejsca</div>
              <input type="number" 
                     className="maxusers-form" 
                     placeholder="Podaj max osób" 
                     value={maxUsers}
                     onChange={(e) => setMaxUsers(e.target.value)}
              /> 
              <div className="text-wrapper-location">Lokalizacja</div>
              <input type="text" 
                     className="location-form" 
                    placeholder="Podaj lokalizację" 
                    value={eventLocation}
                    onChange={(e) => setEventLocation(e.target.value)}
             /> 
              <div className="text-wrapper-mintemp">Minimalna temperatura</div>
              <input type="number" 
                     className="mintemp-form" 
                     placeholder="Ile °C?" 
                     value={eventMinTemperature}
                     onChange={(e) => setMinTemperature(e.target.value)}
              /> 
              <div className="text-wrapper-maxtemp">Maksymalna temperatura</div>
              <input type="number"
                    className="maxtemp-form" 
                    placeholder="Ile °C?" 
                    value={eventMaxTemperature}
                    onChange={(e) => setMaxTemperature(e.target.value)}
              /> 
              <div className="text-wrapper-price">Cena</div>
              <input type="number"
                     className="price-form"
                     placeholder="Podaj cenę?"
                     value={eventPrice}
                     onChange={(e) => setEventPrice(e.target.value)}
              /> 
              <div className="text-wrapper-description">Opis</div>
              <input type="text"
                     className="description-form"
                     placeholder="Podaj opis zajęć"
                     value={eventDescription}
                     onChange={(e) => setEventDescription(e.target.value)}
              /> 
              <div className="text-wrapper-name">Nazwa</div>
              <input type="text" 
                     className="name-form" 
                     placeholder="Jaki to typ zajęć?" 
                     value={eventName}
                     onChange={(e) => setEventName(e.target.value)}
              /> 
      
              <button className="button-add" onClick={handleSubmit} style={{ color: '#ffffff', lineHeight: '0.7'  }}>
                  +
              </button>
            </div>
            <div className="header-form">
              <div className="div-wrapper">
                <p className="p">W celu dodania zajęć wypełnij poniższy formularz:</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  };
  
export default AdminPanel;  