import React from "react";
import "./Main.css";
import "../app/globals.css";

const Main = () => {
  return (
    <div className="main">
      <div className="container">
        <div className="overlap">
          <div className="overlap-group">
            {/*<img className="line" alt="Line" src="https://c.animaapp.com/j7c1j9sQ/img/line-3.svg" />*/}
            <img
              className="laptop"
              alt="Laptop"
              src="https://c.animaapp.com/j7c1j9sQ/img/laptop.png"
            />
            <p className="want-to-book">
              <span className="text-wrapper">
                Chcesz zarezerwować miejsce na zajęciach ?<br />
              </span>
              <span className="span">Pomożemy Ci w tym!</span>
            </p>
            <p className="p">
              ReserveTheWeather to aplikacja webowa umożliwiająca rezerwację
              miejsc na zajęciach w oparciu o dane pogodowe. Pogoda nie sprzyja?
              To nie problem, u nas przełożysz zajęcia lub otrzymasz zniżkę!{" "}
            </p>
            <div className="button-next">
              <div className="overlap-group-2">
                <a href="/UserAuth">
                  <div className="button-next-2" />
                  <div className="text-wrapper-2">Dalej</div>
                </a>
              </div>
            </div>
            <img
              className="logo"
              alt="Logo"
              src="https://c.animaapp.com/j7c1j9sQ/img/logo.png"
            />
          </div>
          <div className="button-account">
            <div className="overlap-2">
              <a href="/WithoutRegistration">
                <img
                  className="house"
                  alt="House"
                  src="https://c.animaapp.com/j7c1j9sQ/img/domek.png"
                />
                <div className="overlap-group-wrapper">
                  <div className="overlap-group-3">
                    <div className="text-wrapper-3">Moje konto</div>
                    <div className="rectangle" />
                  </div>
                </div>
              </a>
            </div>
          </div>
          <a href="/Contact">
            <div className="button-contact">
              <div className="wrapper">
                <div className="text-wrapper-4">Kontakt</div>
              </div>
            </div>
          </a>
          <a href="/Information">
            <div className="button-information">
              <div className="wrapper">
                <div className="text-wrapper-5">Informacje</div>
              </div>
            </div>
          </a>
        </div>
        <p className="text-wrapper-6">
          © 2024 ReserveTheWeather. All rights reserved.
        </p>
      </div>
    </div>
  );
};

export default Main;
