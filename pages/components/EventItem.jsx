// components/EventItem.jsx
import React, { useState, useEffect } from 'react';
import './EventItem.css';
import api from '../../api';

const EventItem = ({ event }) => {
  const [isCancelling, setIsCancelling] = useState(false);
  const [isSigningUp, setIsSigningUp] = useState(false);
  const [weather, setWeather] = useState(null);

  const apiBaseUrl = process.env.REACT_APP_API_BASE_URL || "http://localhost:8080";
  
  const isToday = (date) => {
    const today = new Date();
    const eventDate = new Date(date);
    return (
      today.getFullYear() === eventDate.getFullYear() &&
      today.getMonth() === eventDate.getMonth() &&
      today.getDate() === eventDate.getDate()
    );
  };
  
  const hasDiscount = () => {
    if (weather && isToday(event.date)) {
      const temperature = weather.main && Math.round(weather.main.temp - 273.15);
      return temperature < event.minTemperature || temperature > event.maxTemperature;
    }
    return false;
  };
  
  const discountedPrice = hasDiscount() ? (event.price * 0.8).toFixed(2) : null;

  const displayPrice = hasDiscount() ? (
    <span>
      <del>{event.price} PLN</del> {discountedPrice} PLN
    </span>
  ) : (
    `${event.price} PLN`
  );
{/*To była pierwsz wersja
  const handleSignUpEvent = async () => {
    try {
      setIsSigningUp(true);

      console.log('Signing up for event with ID:', event.id);

      const response = await fetch(`${apiBaseUrl}/api/events/signup/${event.id}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
      });

      console.log('Response:', response);

      if (response.ok) {
        console.log('User signed up successfully');
        // Tutaj dodam kod do odświeżania informacji o wydarzeniu lub listy wydarzeń
      } else {
        console.error('Failed to sign up for event');
        console.log('Error details:', response.status, response.statusText);
      }
    } catch (error) {
      console.error('Error signing up for event:', error);
      console.log('Error details:', error);
    } finally {
      setIsSigningUp(false);
      console.log('Sign-up process completed');
    }
  };
*/}


const handleSignUpEvent = async () => {
  try {
    setIsSigningUp(true);
    console.log('Signing up for event with ID:', event.id);

    const token = localStorage.getItem('token');
    console.log('Token:', token);

    const response = await api.post(`/events/signup/${event.id}`, null, {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json',
      },
    });
    


  
    console.log('Response:', response);

    if (response.status === 200) {
      console.log('User signed up successfully');
      //tu ewentualnie dorobie odświeżanie strony
    } else {
      console.error('Failed to sign up for event');
      console.log('Error details:', response.status, response.statusText);
    }
  } catch (error) {
    console.error('Error signing up for event:', error);
    console.log('Error details:', error);
  } finally {
    setIsSigningUp(false);
    console.log('Sign-up process completed');
  }
};



  const handleCancelEvent = async () => {
    try {
      console.log('Cancelling event with ID:', event.id);

      const response = await fetch(`${apiBaseUrl}/api/user/myevents/cancell/${event.id}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
      });

      console.log('Response:', response);

      if (response.ok) {
        console.log('Event cancelled successfully');
        // Tutaj jeszcze dodam kod do odświeżania listy wydarzeń po pomyślnym usunięciu
      } else {
        console.error('Failed to cancel event');
        console.log('Error details:', response.status, response.statusText);
      }
    } catch (error) {
      console.error('Error cancelling event:', error);
      console.log('Error details:', error);
    } finally {
      setIsCancelling(false);
      console.log('Cancellation process completed');
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      const maxRetries = 3;
      let retries = 0;
  
      while (retries < maxRetries) {
        try {
          const apiKey = '2a22ea7242da86deb44ea46357b5236d';
          const city = 'Krakow';
          const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}`;
  
          const response = await fetch(apiUrl);
  
          if (response.ok) {
            const data = await response.json();
            setWeather(data);
            break; // Wyjdź z pętli, jeśli dane zostały pobrane pomyślnie
          } else if (response.status === 429) {
            // Jeśli limit zapytań, opóźnij i spróbuj ponownie
            await new Promise(resolve => setTimeout(resolve, 1000));
            retries++;
          } else {
            throw new Error(`Failed to fetch data. Status: ${response.status}`);
          }
        } catch (error) {
          console.error('Error during API request:', error);
          break; // Wyjdź z pętli w przypadku innych błędów
        }
      }
    };
  
    fetchData();
  }, []); 

  return (
    <div className="event-item">
      <h3>{event.name}</h3>
      <p>ID: {event.id}</p>
      <p>Data: {event.date}</p>
      <p>Godzina: {event.time}</p>
      <p>Czas trwania: {event.duration} godzin</p>
      <p>Opis: {event.description}</p>
      <p>Lokalizacja: <img src="https://c.animaapp.com/t0STnSju/img/map-2@2x.png" alt="Map Icon" /> {event.location}</p>
      <p>Limit uczestników: {event.maxUsers}</p>
      <p>Min temperatura: {event.minTemperature} °C</p>
      <p>Max temperatura: {event.maxTemperature} °C</p>
      <p>Cena: {displayPrice}</p>
      <p>Uczestnicy: {event.signedUsers}</p>
      <p>Zła pogoda: {hasDiscount() ? 'Tak' : 'Nie'}</p>
      <p>Możliwa zniżka: {hasDiscount() ? 'Tak' : 'Nie'}</p>
{/*Temperatura z API to temperatura z dnia obecnego, pójdzie do usunięcia, ale na razie zostawiam dla testów*/}
      {weather && (
        <div className="weather-forecast-content">
          <p>Temperatura z API: {weather.main && Math.round(weather.main.temp - 273.15)}°C</p>
        </div>
      )}

      <button className='Odwolaj'
       style = {{    
        backgroundColor: '#8faeca',
        color: '#ffffff', 
        boxShadow: '0px 4px 4px #00000040',
        border: '#8faeca',  
        marginLeft: '0px',
        fontFamily: 'Source Serif Pro, serif'}}
      onClick={handleCancelEvent}
      disabled={isCancelling} 
      >
      Odwołaj
      </button>

      <button className='Zapisz'
      style = {{    
        backgroundColor: '#8faeca',
        color: '#ffffff', 
        boxShadow: '0px 4px 4px #00000040',
        border: '#8faeca',  
        marginLeft: '10px',
        fontFamily: 'Source Serif Pro, serif'}}
        onClick={handleSignUpEvent}
        disabled={isSigningUp}
      >
        Zapisz
      </button>
    </div>
  );
};

export default EventItem;