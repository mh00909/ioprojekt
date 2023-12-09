import React from 'react';
import "./ReservationData.css";
import "../app/globals.css";
import WeatherForecast from './components/WeatherForecast';

const ReservationData = () => {
  return (
    <div className="reservation-data">
      <div className="container">
        <p className="text-wrapper">© 2024 ReserveTheWeather. All rights reserved.</p>
        <div className="overlap">
          <div className="overlap-group">
            <img className="background" alt="Background" src="https://c.animaapp.com/RraaDVLa/img/tlo.png" />
            <div className="discount">
              <div className="overlap-group-2">
                <div className="rectangle" />
                <div className="discount-white" />
                <div className="text-wrapper-2">Zniżka</div>
              </div>
            </div>
            <img className="cancel" alt="Cancel" src="https://c.animaapp.com/RraaDVLa/img/odwolaj@2x.png" />
            <div className="group">
              <div className="card">
                <div className="overlap-2">
                  <div className="activity-wrapper">
                    <div className="activity">
                      <div className="overlap-group-3">
                        <div>
                          <img
                            className="status-button"
                            alt="Status button"
                            src="https://c.animaapp.com/RraaDVLa/img/status-guzik@2x.png"
                          />
                        </div>
                        <div className="text-wrapper-3">miejsca</div>
                        <div className="text-wrapper-4">lokalizacjia</div>
                        <div className="text-wrapper-5">Rodzaj aktywności</div>
                        <div className="overlap-3">
                          <div className="text-wrapper-6">Dzień</div>
                          <div className="text-wrapper-7">Godzina</div>
                        </div>
                        <div className="text-wrapper-8">Czas trwania</div>
                      </div>
                    </div>
                  </div>
                  <img className="map" alt="Map" src="https://c.animaapp.com/RraaDVLa/img/map@2x.png" />
                  <img className="users" alt="Users" src="https://c.animaapp.com/RraaDVLa/img/users@2x.png" />
                </div>
              </div>
            </div>
            <div className="overlap-wrapper">
              <div className="container-wrapper">
                <div className="text-wrapper-9">Dane rezerwacji:</div>
              </div>
            </div>
            <img className="line" alt="Line" src="https://c.animaapp.com/RraaDVLa/img/line2.svg" />
            <img className="img" alt="Line" src="https://c.animaapp.com/RraaDVLa/img/line-4.svg" />
            <img className="logo" alt="Logo" src="https://c.animaapp.com/RraaDVLa/img/logo.png" />
          </div>
          <a href="/Account">
            <div className="account-button">
              <div className="overlap-4">
                <img className="home" alt="Home" src="https://c.animaapp.com/RraaDVLa/img/domek.png" />
                <div className="overlap-group-wrapper">
                  <div className="overlap-group-4">
                    <div className="text-wrapper-10">Moje konto</div>
                    <div className="rectangle-2" />
                  </div>
                </div>
              </div>
            </div>
          </a>
          <a href="/Contact">
            <div className="contact-button">
              <div className="overlap-5">
                <div className="text-wrapper-11">Kontakt</div>
              </div>
            </div>
          </a>
          <a href="/Information">
            <div className="information-button">
              <div className="overlap-5">
                <div className="text-wrapper-12">Informacje</div>
              </div>
            </div>
          </a>
        </div>
      </div>
      <WeatherForecast />
    </div>
  );
};

export default ReservationData;