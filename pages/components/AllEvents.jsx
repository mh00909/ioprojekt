import React, { useState } from "react";
import EventItem from "./EventItem";
import "./AllEvents.css";
import { relative } from "path";


/**
 * Komponent `AllEvents` reprezentuje widok zawierający wszystkie wydarzenia w danym dniu.
 * @component
 * @param {Object[]} allEvents - Tablica zawierająca wszystkie dostępne wydarzenia.
 * @param {string} selectedDate - Data, dla której wyświetlane są wydarzenia.
 * @returns {JSX.Element} - Zwraca element JSX reprezentujący listę wydarzeń w danym dniu.
 */
const AllEvents = ({ allEvents, selectedDate }) => {
  const [currentIndex, setCurrentIndex] = useState(0);

  // Filtrowanie wydarzeń na podstawie wybranej daty
  const filteredEvents = allEvents.filter(
      (event) => event.date === selectedDate
  );

    /**
     * Obsługuje kliknięcie przycisku poprzedniego wydarzenia.
     */
  const handlePrevClick = () => {
    setCurrentIndex((prevIndex) => (prevIndex > 0 ? prevIndex - 1 : 0));
  };

  /**
   * Obsługuje kliknięcie przycisku następnego wydarzenia.
   */
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
                    //border: "1px solid transparent",
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