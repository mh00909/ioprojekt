
// components/AllEvents.jsx

import React from 'react';
import EventItem from './EventItem';
import './AllEvents.css'; // Dodaj import do pliku ze stylami

const AllEvents = ({ allEvents }) => (
  <div className="container">
    {allEvents.map((event) => (
      <EventItem key={event.id} event={event} className="event-item" />
    ))}
  </div>
);

export default AllEvents;