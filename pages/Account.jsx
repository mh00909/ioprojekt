import React, { useState, useEffect } from "react";
import "./Account.css";
import api from "../api";
import DateSelector from './components/DateSelector';


const Account = ({user}) => {
  const apiBaseUrl = process.env.REACT_APP_API_BASE_URL || "http://localhost:8080";
  console.log('Właściwość user w komponencie Konto:', user);

  const userLoginEndpoint = `${apiBaseUrl}/Account`;
  const [userData, setUserData] = useState([]);
  const [selectedDate, setSelectedDate] = useState("2023-12-30");
  const [isUserDataLoaded, setIsUserDataLoaded] = useState(false);

  const [error, setError] = useState(""); 

  useEffect(() => {
    // Funkcja do pobrania danych zalogowanego użytkownika z backendu
    const fetchUserData = async () => {
      try {
        const token = localStorage.getItem('token');
        const response = await api.get('/api/auth/checkLogged', {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      });
      console.log("Dane użytkownika: ", response.data); 
      if(response.status == 200){

        setUserData(response.data);
        console.log("Dane użytkownika: ", response.data); 
        localStorage.setItem('login', response.data.name);
        console.log('login zapisany: ', localStorage.getItem('login'));
        console.log('token zapisany: ', localStorage.getItem('token'));
        setIsUserDataLoaded(true);

      }
      else{
        console.error("Nie udało się pobrać danych")
      }
       
      } catch (error) {
        console.error("Błąd pobierania danych użytkownika:", error);
      }
    };

    // Wywołaj funkcję pobierania danych przy pierwszym renderowaniu komponentu
    fetchUserData();
  }, []); // Pusta tablica oznacza, że useEffect zostanie uruchomiony tylko raz

{

  useEffect(() => {
    const fetchAllEvents = async () => {
      try {

        const response = await api.get(`/api/user/myEventsOnDay?date=${selectedDate}&name=${localStorage.getItem('login')}`);
        console.log('Otrzymano zajęcia użytkownika o loginie ', localStorage.getItem('login', " : ", response.data[0]));

      } catch (error) {
        console.error('Błąd podczas pobierania danych:', error);
      }
    };

    if (isUserDataLoaded) {
      fetchAllEvents();
    }
    
  }, [selectedDate, isUserDataLoaded]);
}
  const handleDateSelection = (date) => {
    setSelectedDate(date);
  };




  return (
    <div className="account">
    <div className="div">
    <div className="selector-style"><DateSelector onSelectDate={handleDateSelection} /></div>

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
          <div className="overlap-wrapper">
            <div className="overlap-8">
              <div className="user-profile" />
              <div className="text-wrapper-11">Profil użytkownika:</div>
            </div>
          </div>
          <div className="text-wrapper-12">
          Login: {userData ? userData.name : "Niezalogowany"}
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
        <button className='LogOut'
          style = {{    
            backgroundColor: '#8faeca',
            color: '#ffffff', 
            boxShadow: '0px 4px 4px #00000040',
            border: '#8faeca',  
            marginLeft: '1080px',
            marginTop: '470px',
            height: '60px',
            width: '325px',
            fontSize: '27px',
            position: 'relative',
            zIndex: '999',
            fontFamily: 'Source Serif Pro, serif'}}
          /* onClick={handleLogOutEvent}*/
        >
        Wyloguj
        </button>

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
