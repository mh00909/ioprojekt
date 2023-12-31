{
  /*
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
*/
}

import React, { useState } from "react";
import EventItem from "./EventItem";
import "./AllEvents.css";
import { relative } from "path";

const AllEvents = ({ allEvents, selectedDate }) => {
  const [currentIndex, setCurrentIndex] = useState(0);

  // Filtrowanie wydarzeń na podstawie wybranej daty
  const filteredEvents = allEvents.filter(
    (event) => event.date === selectedDate
  );

  const handlePrevClick = () => {
    setCurrentIndex((prevIndex) => (prevIndex > 0 ? prevIndex - 1 : 0));
  };

  const handleNextClick = () => {
    setCurrentIndex((prevIndex) =>
      prevIndex < filteredEvents.length - 1 ? prevIndex + 1 : prevIndex
    );
  };

  return (
    <div className="container">
      <div className="events-container">
        {filteredEvents.length > 0 ? (
          <>
            <div className="navigation-buttons">
              <button
                className="prev-button"
                style={{
                  backgroundColor: "#8faeca",
                  color: "#ffffff",
                  boxShadow: "0px 4px 4px #00000040",
                  border: "#8faeca",
                  marginLeft: "0px",
                  fontFamily: "Source Serif Pro, serif",
                }}
                onClick={handlePrevClick}
              >
                &lt;{" "}
              </button>
              <button
                className="next-button"
                style={{
                  backgroundColor: "#8faeca",
                  color: "#ffffff",
                  boxShadow: "0px 4px 4px #00000040",
                  border: "#8faeca",
                  marginLeft: "0px",
                  fontFamily: "Source Serif Pro, serif",
                }}
                onClick={handleNextClick}
              >
                {" "}
                &gt;
              </button>
            </div>
            <EventItem
              key={filteredEvents[currentIndex].id}
              event={filteredEvents[currentIndex]}
              className="event-item"
            />
          </>
        ) : (
          <p
            style={{
              fontSize: "large",
              position: "relative",
              zIndex: "200",
              border: "1px solid #ccc",
              backgroundColor: "#ffffff",
              padding: "20px",
              borderRadius: "50px",
              border: "1px solid transparent",
              boxShadow: "0px 4px 4px #00000040",
            }}
          >
            Niestety w ten dzień nie ma planowanych zajęć :( Sprawdź inną datę.
          </p>
        )}
      </div>
    </div>
  );
};

export default AllEvents;
