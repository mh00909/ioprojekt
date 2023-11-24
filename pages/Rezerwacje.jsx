import React, { useState } from 'react';
import "./Rezerwacje.css";
import "../app/globals.css"
import DateSelector from './DateSelector';
import "./DateSelector.css";


const Rezerwacje = () => {
  return (
    <div className="rezerwacje">
      <div className="overlap-wrapper">
        <div className="overlap">
          <div className="overlap-group">
            <p className="text-wrapper">© 2024 ReserveTheWeather. All rights reserved.</p>
            <img className="line" alt="Line" src="https://c.animaapp.com/iiOpoSVt/img/line-4.svg" />
            <img className="tlo" alt="Tlo" src="https://c.animaapp.com/iiOpoSVt/img/tlo.png" />
            <div className="div">
            <DateSelector/></div>
            <img className="logo" alt="Logo" src="https://c.animaapp.com/iiOpoSVt/img/logo.png" />
            {/*
            <div className="karteczka">
              <div className="overlap-2">
                <img
                  className="status-guzik"
                  alt="Status guzik"
                  src="https://c.animaapp.com/iiOpoSVt/img/status-guzik-2@2x.png"
                />
                <div className="text-wrapper-2">miejsca</div>
                <div className="overlap-group-2">
                  <div className="text-wrapper-3">lokalizacja</div>
                  <img className="map" alt="Map" src="https://c.animaapp.com/iiOpoSVt/img/map-2@2x.png" />
                </div>
                <div className="text-wrapper-4">Rodzaj zajęć</div>
                <div className="overlap-3">
                  <div className="text-wrapper-5">Dzień</div>
                  <div className="text-wrapper-6">Godzina</div>
                </div>
                <div className="text-wrapper-7">Czas trwania</div>
                <img className="users" alt="Users" src="https://c.animaapp.com/iiOpoSVt/img/users-2@2x.png" />
              </div>
            </div>*
            <div className="overlap-group-wrapper">
              <div className="overlap-2">
                <img
                  className="status-guzik"
                  alt="Status guzik"
                  src="https://c.animaapp.com/iiOpoSVt/img/status-guzik-2@2x.png"
                />
                <div className="text-wrapper-2">miejsca</div>
                <div className="overlap-group-2">
                  <div className="text-wrapper-3">lokalizacja</div>
                  <img className="map" alt="Map" src="https://c.animaapp.com/iiOpoSVt/img/map-2@2x.png" />
                </div>
                <div className="text-wrapper-4">Rodzaj zajęć</div>
                <div className="overlap-3">
                  <div className="text-wrapper-5">Dzień</div>
                  <div className="text-wrapper-6">Godzina</div>
                </div>
                <div className="text-wrapper-7">Czas trwania</div>
                <img className="users" alt="Users" src="https://c.animaapp.com/iiOpoSVt/img/users-2@2x.png" />
              </div>
            </div>
            <div className="div-wrapper">
              <div className="overlap-2">
                <img
                  className="status-guzik"
                  alt="Status guzik"
                  src="https://c.animaapp.com/iiOpoSVt/img/status-guzik-2@2x.png"
                />
                <div className="text-wrapper-2">miejsca</div>
                <div className="overlap-group-2">
                  <div className="text-wrapper-3">lokalizacja</div>
                  <img className="map" alt="Map" src="https://c.animaapp.com/iiOpoSVt/img/map-2@2x.png" />
                </div>
                <div className="text-wrapper-4">Rodzaj zajęć</div>
                <div className="overlap-3">
                  <div className="text-wrapper-5">Dzień</div>
                  <div className="text-wrapper-6">Godzina</div>
                </div>
                <div className="text-wrapper-7">Czas trwania</div>
                <img className="users" alt="Users" src="https://c.animaapp.com/iiOpoSVt/img/users-2@2x.png" />
              </div>
  </div>*/}
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
