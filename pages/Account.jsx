{/**/}import React, { useState, useEffect } from "react";
import "./Account.css";
import api from "../api";


const Account = ({user}) => {
  const apiBaseUrl = process.env.REACT_APP_API_BASE_URL || "http://localhost:8080";
  console.log('Właściwość user w komponencie Konto:', user);

  const userLoginEndpoint = `${apiBaseUrl}/Konto`;
  const [userData, setUserData] = useState([]);

  const [error, setError] = useState(""); 




  useEffect(() => {
    // Funkcja do pobrania danych zalogowanego użytkownika z backendu
    const fetchUserData = async () => {
      try {
        const response = await api.get('/Konto');
        setUserData(response.data);
      } catch (error) {
        console.error("Błąd pobierania danych użytkownika:", error);
      }
    };

    // Wywołaj funkcję pobierania danych przy pierwszym renderowaniu komponentu
    fetchUserData();
  }, []); // Pusta tablica oznacza, że useEffect zostanie uruchomiony tylko raz

  return (
    <div className="account">
    <div className="div">
      <p className="text-wrapper">© 2024 ReserveTheWeather. All rights reserved.</p>
      <div className="overlap">
        <div className="overlap-group">
          <img className="profile" alt="Profil" src="https://c.animaapp.com/DjotD7lA/img/profil.png" />
          <div className="text-wrapper-2">Dane rezerwacji:</div>
          <a href="/Reservations">
          <img className="rzerwuje" alt="Rzerwuje" src="https://c.animaapp.com/DjotD7lA/img/rzerwuje@2x.png" />
          </a>
          <div className="opis">
            <div className="overlap-2">
              <img className="strzalka" alt="Strzalka" src="https://c.animaapp.com/DjotD7lA/img/strzalka.svg" />
              <div className="overlap-group-wrapper">
                <div className="overlap-group-2">
                  <div className="opis-2" />
                  <p className="p">Żeby dokonać nowych rezerwacji kliknij przycisk</p>
                </div>
              </div>
            </div>
          </div>
          <img className="line" alt="Line" src="https://c.animaapp.com/DjotD7lA/img/line-4.svg" />
          <img
            className="add-header"
            alt="Dodaj nagwek"
            src="https://c.animaapp.com/DjotD7lA/img/dodaj-nag--wek--12--1.png"
          />
          <div className="card">
            <div className="overlap-3">
              <div className="group">
                <div className="div-wrapper">
                  <div className="overlap-group-3">
                    <div className="overlap-4">
                      <div className="overlap-5">
                        <img
                          className="status-guzik"
                          alt="Status guzik"
                          src="https://c.animaapp.com/DjotD7lA/img/status-guzik@2x.png"
                        />
                        <div className="overlap-6">
                          <div className="text-wrapper-5">Rodzaj zajęć</div>
                        </div>
                      </div>
                      <div className="text-wrapper-6">miejsca</div>
                      <div className="text-wrapper-7">lokalizacja</div>
                    </div>
                    <div className="overlap-7">
                      <div className="text-wrapper-8">Dzień</div>
                      <div className="text-wrapper-9">Godzina</div>
                    </div>
                    <div className="text-wrapper-10">Czas trwania</div>
                  </div>
                </div>
              </div>
              <img className="map" alt="Map" src="https://c.animaapp.com/DjotD7lA/img/map@2x.png" />
              <img className="users" alt="Users" src="https://c.animaapp.com/DjotD7lA/img/users@2x.png" />
            </div>
          </div>
          <div className="overlap-wrapper">
            <div className="overlap-8">
              <div className="user-profile" />
              <div className="text-wrapper-11">Profil użytkownika:</div>
            </div>
          </div>
          <div className="text-wrapper-12">
            Login: {user ? user.name : "Niezalogowany"}
          </div>
        </div>
        <div className="button-account">
          <div className="overlap-9">
            <img className="Home" alt="Domek" src="https://c.animaapp.com/DjotD7lA/img/domek.png" />
            <div className="button-account-2">
              <div className="overlap-group-4">
                <div className="text-wrapper-13">Moje Konto</div>
                <div className="div-2" />
              </div>
            </div>
          </div>
        </div>
        <a href="/Contact">
        <div className="button-contact">
          <div className="overlap-10">
            <div className="text-wrapper-14">Kontakt</div>
          </div>
        </div>
        </a>
        <a href="/Information">
        <div className="button-information">
          <div className="overlap-10">
            <div className="text-wrapper-15">Informacje</div>
          </div>
        </div>
        </a>
      </div>
    </div>
  </div>
  );
};

export default Account;
{/**/}