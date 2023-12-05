import React, { useEffect, useState } from 'react';
import "./Dane_rezerwacji.css";
import "../app/globals.css"
import PrognozaPogody from './components/PrognozaPogody';




const Dane_Rezerwacji = () => {
  return (
    <div className="dane-rezerwacji">
    <div className="div">
      <p className="text-wrapper">© 2024 ReserveTheWeather. All rights reserved.</p>
      <div className="overlap">
        <div className="overlap-group">
          <img className="tlo" alt="Tlo" src="https://c.animaapp.com/RraaDVLa/img/tlo.png" />
          <div className="znizka">
            <div className="overlap-group-2">
              <div className="rectangle" />
              <div className="znizka-bialy" />
              <div className="text-wrapper-2">Zniżka</div>
            </div>
          </div>
          <img className="odwolaj" alt="Odwołaj" src="https://c.animaapp.com/RraaDVLa/img/odwolaj@2x.png" />
          <div className="group">
            <div className="karteczka">
              <div className="overlap-2">
                <div className="zajecie-wrapper">
                  <div className="zajecie">
                    <div className="overlap-group-3">
                      <div>
                        <img
                          className="status-guzik"
                          alt="Status guzik"
                          src="https://c.animaapp.com/RraaDVLa/img/status-guzik@2x.png"
                        />
                      </div>
                      <div className="text-wrapper-3">miejsca</div>
                      <div className="text-wrapper-4">lokalizacja</div>
                      <div className="text-wrapper-5">Rodzaj zajęć</div>
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
            <div className="div-wrapper">
              <div className="text-wrapper-9">Dane rezerwacji:</div>
            </div>
          </div>
          <img className="line" alt="Line" src="https://c.animaapp.com/RraaDVLa/img/line2.svg" />
          <img className="img" alt="Line" src="https://c.animaapp.com/RraaDVLa/img/line-4.svg" />
          <img className="logo" alt="Logo" src="https://c.animaapp.com/RraaDVLa/img/logo.png" />
        </div>
        <a href="/Konto">
        <div className="przycisk-konto">
          <div className="overlap-4">
            <img className="domek" alt="Domek" src="https://c.animaapp.com/RraaDVLa/img/domek.png" />
            <div className="overlap-group-wrapper">
              <div className="overlap-group-4">
                <div className="text-wrapper-10">Moje Konto</div>
                <div className="rectangle-2" />
              </div>
            </div>
          </div>
        </div>
        </a>
        <a href="/Kontakt">
        <div className="przycisk-kontakt">
          <div className="overlap-5">
            <div className="text-wrapper-11">Kontakt</div>
          </div>
        </div>
        </a>
        <a href="/Informacje">
        <div className="przycisk-informacje">
          <div className="overlap-5">
            <div className="text-wrapper-12">Informacje</div>
          </div>
        </div>
        </a>
      </div>
    </div>
    <PrognozaPogody />
  </div>
);
};

export default Dane_Rezerwacji;