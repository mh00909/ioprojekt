import React, { useState, useEffect } from 'react';
import "./Rezerwacje.css";
import "../app/globals.css"
import DateSelector from './components/DateSelector';
import api from "../api";
import AllEvents from './components/AllEvents';

const Rezerwacje = () => {
  const apiBaseUrl = process.env.REACT_APP_API_BASE_URL || "http://localhost:8080";
  const allEventsEndpoint = `${apiBaseUrl}/api/events/all`;

  const [allEvents, setAllEvents] = useState([]);
  const [selectedDate, setSelectedDate] = useState("");

  const [error, setError] = useState(""); 

  useEffect(() => {
    const fetchAllEvents = async () => {
      try {
        const response = await api.get(`/api/events/all?date=${selectedDate}`);
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

  return (
    <div className="rezerwacje">
      <div className="overlap-wrapper">
        <div className="overlap">
          <div className="overlap-group">
            <p className="text-wrapper">© 2024 ReserveTheWeather. All rights reserved.</p>
            <img className="line" alt="Line" src="https://c.animaapp.com/iiOpoSVt/img/line-4.svg" />
            <div className="all-events-container">
              <AllEvents allEvents={allEvents} selectedDate={selectedDate} />
            </div>
            <img className="tlo" alt="Tlo" src="https://c.animaapp.com/iiOpoSVt/img/tlo.png" />
            <div className="div">
              <DateSelector onSelectDate={handleDateSelection} />
            </div>
            <img className="logo" alt="Logo" src="https://c.animaapp.com/iiOpoSVt/img/logo.png" />
          </div>
          <a href="/Konto">
            <div className="przycisk-konto">
              <div className="overlap-4">
                <img className="domek" alt="Domek" src="https://c.animaapp.com/iiOpoSVt/img/domek.png" />
                <div className="przycisk-konto-2">
                  <div className="overlap-group-3">
                    <div className="text-wrapper-8">Moje Konto</div>
                    <div className="rectangle" />
                  </div>
                </div>
              </div>
            </div>
          </a>
          <a href="/Kontakt">
            <div className="przycisk-kontakt">
              <div className="overlap-5">
                <div className="text-wrapper-9">Kontakt</div>
              </div>
            </div>
          </a>
          <a href="/Informacje">
            <div className="przycisk-informacje">
              <div className="overlap-5">
                <div className="text-wrapper-10">Informacje</div>
              </div>
            </div>
          </a>
        </div>
      </div>
    </div>
  );
};

export default Rezerwacje;
