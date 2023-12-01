// components/EventItem.jsx

import React from 'react';
import './EventItem.css'; 
import mapImage from '../Images/map.png';

const EventItem = ({ event }) => (
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
  </div>
);

export default EventItem;
