import React, { useState, useEffect } from "react";
import DateSelector from './DateSelector';
import "./DateSelector.css";
import "./RezerwacjeAdmin.css";
import api from "../api";

const RezerwacjeAdmin = () => {
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
  const apiBaseUrl = process.env.REACT_APP_API_BASE_URL || "http://localhost:8080";
  {/*Tu pod spodem jak bedziemy mieć endpoint do dodania zajęć będzie można go wpisać*/}
  const addEventEndpoint = `${apiBaseUrl}/addEvent`;

  {/*Tu ma się dać dodawać zajęcia*/}

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
      const response = await api.post('/addEvent', formData);
      if(response.data == "Strona domowa"){
        console.log('Udało się dodać zajęcia', response.data)
        window.location.href = '/RezerwacjeAdmin';
      }
      else{
        // niepoprawne dane 
        window.location.href = '/RezerwacjeAdmin';
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
        console.log("Przed pobraniem danych");
        const response = await api.get(allEventsEndpoint);
        setAllEvents(response.data);
        console.log("Dane pobrane:", response.data);
      } catch (error) {
        console.error("Błąd podczas pobierania danych:", error);
        setError("Błąd podczas pobierania danych. Spróbuj ponownie.");
      }
    };
  
    fetchAllEvents();
  }, [allEventsEndpoint]);

    return (
      <div className="rezerwacje-admin">




            <div className="wydarzenie">
      <h2>Wszystkie zajęcia</h2>
      {allEvents.map((event) => (
        <div key={event.id} className="overlap-2">
          <div className="text-wrapper-5">Nazwa: {event.nazwa}</div>
          <div className="overlap-4">
            <div className="text-wrapper-6">Data: {event.data}</div>
            <div className="text-wrapper-7">Godzina: {event.godzina}</div>
          </div>
          {/* Dodaj pozostałe informacje */}
          {/* ... */}
        </div>
      ))}
    </div>

        <div className="div">
          <p className="text-wrapper">© 2024 ReserveTheWeather. All rights reserved.</p>
          <div className="overlap">
            <div className="overlap-group">
              <img className="line" alt="Line" src="https://c.animaapp.com/t0STnSju/img/line-4.svg" />
              <img className="tlo" alt="Tlo" src="https://c.animaapp.com/t0STnSju/img/tlo.png" />
              <div className="text-wrapper-2">Widok administratora</div>
              <div className="selector-style"><DateSelector/></div>
              <img className="logo" alt="Logo" src="https://c.animaapp.com/t0STnSju/img/logo.png" />
              <div className="wydarzenie">
                <div className="overlap-2">
                  <div className="text-wrapper-3">miejsca</div>
                  <div className="overlap-3">
                    <div className="text-wrapper-4">lokalizacja</div>
                    <img className="map" alt="Map" src="https://c.animaapp.com/t0STnSju/img/map-2@2x.png" />
                  </div>
                  <div className="text-wrapper-5">Nazwa</div>
                  <div className="overlap-4">
                    <div className="text-wrapper-6">Dzień</div>
                    <div className="text-wrapper-7">Godzina</div>
                  </div>
                  <div className="text-wrapper-8">Czas trwania</div>
                  <img className="users" alt="Users" src="https://c.animaapp.com/t0STnSju/img/users-2@2x.png" />
                  <div className="przycisk-usun">
                    <div className="overlap-group-2">
                      <img
                        className="status-guzik"
                        alt="Status guzik"
                        src="https://c.animaapp.com/t0STnSju/img/status-guzik-6@2x.png"
                      />
                      <img className="trash" alt="Trash" src="https://c.animaapp.com/t0STnSju/img/trash-1-2@2x.png" />
                      <div className="text-wrapper-9">usuń</div>
                    </div>
                  </div>
                  <div className="przycisk-przeloz">
                    <div className="overlap-5">
                      <img
                        className="img"
                        alt="Status guzik"
                        src="https://c.animaapp.com/t0STnSju/img/status-guzik-5@2x.png"
                      />
                      <img className="pencil" alt="Pencil" src="https://c.animaapp.com/t0STnSju/img/pencil-1-2@2x.png" />
                      <div className="text-wrapper-10">przełóż</div>
                    </div>
                  </div>
                  <div className="overlap-6">
                    <img
                      className="data-rect"
                      alt="Data rect"
                      src="https://c.animaapp.com/t0STnSju/img/data-rect-2.png"
                    />
                    <img
                      className="godzina-rect"
                      alt="Godzina rect"
                      src="https://c.animaapp.com/t0STnSju/img/data-rect-2.png"
                    />
                    <div className="text-wrapper-11">Data:</div>
                    <div className="text-wrapper-12">Godzina:</div>
                  </div>
                </div>
              </div>
              <div className="overlap-group-wrapper">
                <div className="overlap-2">
                  <div className="text-wrapper-3">miejsca</div>
                  <div className="overlap-3">
                    <div className="text-wrapper-4">lokalizacja</div>
                    <img className="map" alt="Map" src="https://c.animaapp.com/t0STnSju/img/map-2@2x.png" />
                  </div>
                  <div className="text-wrapper-5">Nazwa</div>
                  <div className="overlap-4">
                    <div className="text-wrapper-6">Dzień</div>
                    <div className="text-wrapper-7">Godzina</div>
                  </div>
                  <div className="text-wrapper-8">Czas trwania</div>
                  <img className="users" alt="Users" src="https://c.animaapp.com/t0STnSju/img/users-2@2x.png" />
                  <div className="przycisk-usun">
                    <div className="overlap-group-2">
                      <img
                        className="status-guzik"
                        alt="Status guzik"
                        src="https://c.animaapp.com/t0STnSju/img/status-guzik-6@2x.png"
                      />
                      <img className="trash" alt="Trash" src="https://c.animaapp.com/t0STnSju/img/trash-1-2@2x.png" />
                      <div className="text-wrapper-9">usuń</div>
                    </div>
                  </div>
                  <div className="przycisk-przeloz">
                    <div className="overlap-5">
                      <img
                        className="img"
                        alt="Status guzik"
                        src="https://c.animaapp.com/t0STnSju/img/status-guzik-5@2x.png"
                      />
                      <img className="pencil" alt="Pencil" src="https://c.animaapp.com/t0STnSju/img/pencil-1-2@2x.png" />
                      <div className="text-wrapper-10">przełóż</div>
                    </div>
                  </div>
                  <div className="overlap-6">
                    <img
                      className="data-rect"
                      alt="Data rect"
                      src="https://c.animaapp.com/t0STnSju/img/data-rect-2.png"
                    />
                    <img
                      className="godzina-rect"
                      alt="Godzina rect"
                      src="https://c.animaapp.com/t0STnSju/img/data-rect-2.png"
                    />
                    <div className="text-wrapper-11">Data:</div>
                    <div className="text-wrapper-12">Godzina:</div>
                  </div>
                </div>
              </div>
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
              <div className="text-wrapper-godzina">Godzina</div>
              <input type="time"
                     className="godzina-form"
                     placeholder="Wprowadź godzinę"
                     value={eventTime}
                     onChange={(e) => setEventTime(e.target.value)}
              /> 
              <div className="text-wrapper-czas">Czas trwania</div>
              <input type="numer"
                     className="czas-form" 
                     placeholder="Wprowadź czas" 
                     value={eventDuration}
                     onChange={(e) => setEventDuration(e.target.value)}
              /> 
              <div className="text-wrapper-miejsca">Miejsca</div>
              <input type="number" 
                     className="miejsce-form" 
                     placeholder="Podaj max osób" 
                     value={maxUsers}
                     onChange={(e) => setMaxUsers(e.target.value)}
              /> 
              <div className="text-wrapper-lokalizacja">Lokalizacja</div>
              <input type="text" 
                     className="lokalizacja-form" 
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
              <div className="text-wrapper-cena">Cena</div>
              <input type="number"
                     className="cena-form"
                     placeholder="Podaj cenę?"
                     value={eventPrice}
                     onChange={(e) => setEventPrice(e.target.value)}
              /> 
              <div className="text-wrapper-opis">Opis</div>
              <input type="text"
                     className="opis-form"
                     placeholder="Podaj opis zajęć"
                     value={eventDescription}
                     onChange={(e) => setEventDescription(e.target.value)}
              /> 
              <div className="text-wrapper-nazwa">Nazwa</div>
              <input type="text" 
                     className="nazwa-form" 
                     placeholder="Jaki to typ zajęć?" 
                     value={eventName}
                     onChange={(e) => setEventName(e.target.value)}
              /> 
      
              <button className="przycisk-dodaj" onClick={handleSubmit} style={{ color: '#ffffff', lineHeight: '0.7'  }}>
                  +
               {/* <div className="overlap-8">
                  <img
                    className="status-guzik-2"
                    alt="Status guzik"
                    src="https://c.animaapp.com/t0STnSju/img/status-guzik@2x.png"
                  />
                  <div className="text-wrapper-19">dodaj</div>
                  <img className="add" alt="Add" src="https://c.animaapp.com/t0STnSju/img/add-1@2x.png" />
    </div>*/}
              </button>
            </div>
            <div className="naglowek-form">
              <div className="div-wrapper">
                <p className="p">W celu dodania zajęć wypełnij poniższy formularz:</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  };
  
export default RezerwacjeAdmin;  