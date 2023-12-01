// components/EventItem.jsx

import React from 'react';

const EventItem = ({ event }) => (
  <div>
    <h3>{event.name}</h3>
    <p>ID: {event.id}</p>
    <p>Date: {event.date}</p>
    <p>Time: {event.time}</p>
    <p>Duration: {event.duration} hours</p>
    <p>Description: {event.description}</p>
    <p>Location: {event.location}</p>
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
