
// components/AllEvents.jsx

import React from 'react';
import EventItem from './EventItem';

const AllEvents = ({ allEvents }) => (
  <div>
    {allEvents.map((event) => (
      <EventItem key={event.id} event={event} />
    ))}
  </div>
);

export default AllEvents;
