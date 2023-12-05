import React from 'react';
import EventItem from './EventItem';
import './AllEvents.css';

const AllEvents = ({ allEvents, selectedDate }) => {
  // Filtrowanie wydarzeń na podstawie wybranej daty
  const filteredEvents = allEvents.filter(event => event.date === selectedDate);

  return (
    <div className="container">
      {filteredEvents.map((event) => (
        <EventItem key={event.id} event={event} className="event-item" />
      ))}
    </div>
  );
};

export default AllEvents;