// components/EventItem.jsx

import React, { useState } from 'react';
import './EventItem.css'; 

const EventItem = ({ event }) => {
  const [isCancelling, setIsCancelling] = useState(false);
  const [isSigningUp, setIsSigningUp] = useState(false);

  const apiBaseUrl = process.env.REACT_APP_API_BASE_URL || "http://localhost:8080";



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
        // Tutaj jescze dodam kod do odświeżania listy wydarzeń po pomyślnym usunięciu
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


  return (
    <div className="event-item">
      <h3>{event.name}</h3>
      <p>ID: {event.id}</p>
      <p>Date: {event.date}</p>
      <p>Time: {event.time}</p>
      <p>Duration: {event.duration} hours</p>
      <p>Description: {event.description}</p>
      <p>Location: <img src="https://c.animaapp.com/t0STnSju/img/map-2@2x.png" alt="Map Icon" /> {event.location}</p>
      <p>Max Users: {event.max_users}</p>
      <p>Min Temperature: {event.min_temperature} °C</p>
      <p>Max Temperature: {event.max_temperature} °C</p>
      <p>Price: {event.price} PLN</p>
      <p>Signed Users: {event.signed_users}</p>
      <p>Bad Weather: {event.bad_weather ? 'Yes' : 'No'}</p>
      <p>Discount: {event.discount ? 'Yes' : 'No'}</p>
      
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
