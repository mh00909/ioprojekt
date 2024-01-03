import React, { useState, useEffect } from "react";
import "./Reservations.css";
import "../app/globals.css";
import DateSelector from "./components/DateSelector";
import api from "../api";
import AllEvents from "./components/AllEvents";


/**
 * Komponent `Reservations` reprezentuje widok rezerwacji użytkownika.
 * @component
 * @returns {JSX.Element} - Zwraca element JSX reprezentujący widok rezerwacji.
 */
const Reservations = () => {
  const apiBaseUrl =
    process.env.REACT_APP_API_BASE_URL || "http://localhost:8080";
  const allEventsEndpoint = `${apiBaseUrl}/api/events/all`;

  const [allEvents, setAllEvents] = useState([]);
  const [selectedDate, setSelectedDate] = useState("2003-12-30"); //Tutaj zmieniam na datę z przeszłości, bo nie może być pusty nawias, wybór faktycznej daty dokonuje się później

  const [error, setError] = useState("");


  /**
   * Efekt pobierający wszystkie wydarzenia na wybranej dacie po załadowaniu komponentu.
   * @function
   * @returns {void}
   */
  useEffect(() => {
    const fetchAllEvents = async () => {
      try {
        const login = localStorage.getItem("login");
        console.log("Login przy Reservations:", login);

        console.log("wybrana data:", selectedDate);
        const response = await api.get(
          `/api/user/allEventsOnDay?date=${selectedDate}&name=${localStorage.getItem(
            "login"
          )}`
        );
        console.log("Pobrano: ", response.data);
        setAllEvents(response.data);
      } catch (error) {
        console.error("Błąd podczas pobierania danych:", error);
      }
    };

    fetchAllEvents();
  }, [selectedDate]);


  /**
   * Obsługuje zdarzenie wyboru daty.
   * @function
   * @param {string} date - Wybrana data.
   * @returns {void}
   */
  const handleDateSelection = (date) => {
    setSelectedDate(date);
  };

  return (
    <div className="reservations">
      <div className="overlap-wrapper">
        <div className="overlap">
          <div className="overlap-group">
            <p className="text-wrapper">
              © 2024 ReserveTheWeather. All rights reserved.
            </p>
            <img
              className="line"
              alt="Line"
              src="https://c.animaapp.com/iiOpoSVt/img/line-4.svg"
            />
            <div className="all-events-container">
              <AllEvents allEvents={allEvents} selectedDate={selectedDate} />
            </div>
            <img
              className="background"
              alt="Tlo"
              src="https://c.animaapp.com/iiOpoSVt/img/tlo.png"
            />
            <div className="div">
              <DateSelector onSelectDate={handleDateSelection} />
            </div>
            <img
              className="logo"
              alt="Logo"
              src="https://c.animaapp.com/iiOpoSVt/img/logo.png"
            />
          </div>
          <a href="/Account">
            <div className="button-account">
              <div className="overlap-4">
                <img
                  className="Home"
                  alt="Domek"
                  src="https://c.animaapp.com/iiOpoSVt/img/domek.png"
                />
                <div className="button-account-2">
                  <div className="overlap-group-3">
                    <div className="text-wrapper-8">Moje Konto</div>
                    <div className="rectangle" />
                  </div>
                </div>
              </div>
            </div>
          </a>
          <a href="/Contact">
            <div className="button-contact">
              <div className="overlap-5">
                <div className="text-wrapper-9">Kontakt</div>
              </div>
            </div>
          </a>
          <a href="/Information">
            <div className="button-information">
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

export default Reservations;
