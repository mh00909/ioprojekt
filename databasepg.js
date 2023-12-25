// Plik databasepg.js
const { Client } = require("pg");

const client = new Client({
  host: "localhost",
  user: "projekt",
  port: 5432,
  password: "weather",
  database: "reservetheweather",
});

client.connect();

// Funkcja usuwająca wydarzenie o określonym ID
const deleteEventById = async (eventId) => {
  try {
    const deleteQuery = "DELETE FROM events WHERE id = $1 RETURNING *";
    const result = await client.query(deleteQuery, [eventId]);

    if (result.rows.length > 0) {
      console.log(`Event with ID ${eventId} deleted successfully.`);
      console.log("Remaining events:", result.rows);
    } else {
      console.log(`Event with ID ${eventId} not found.`);
    }
  } catch (error) {
    console.error("Error deleting event:", error.message);
  }
};

// Funkcja zmieniająca datę wydarzenia o określonym ID
const updateEventDateById = async (eventId, newDate) => {
  try {
    const updateQuery = "UPDATE events SET date = $1 WHERE id = $2 RETURNING *";
    const result = await client.query(updateQuery, [newDate, eventId]);

    if (result.rows.length > 0) {
      console.log(`Event date for ID ${eventId} updated successfully.`);
      console.log("Updated event details:", result.rows);
    } else {
      console.log(`Event with ID ${eventId} not found.`);
    }
  } catch (error) {
    console.error("Error updating event date:", error.message);
  }
};

// Odczyt operacji (delete/update), ID wydarzenia i, w przypadku update, nowej daty z konsoli
const readline = require("readline").createInterface({
  input: process.stdin,
  output: process.stdout,
});

readline.question(
  "Enter the operation (delete/update): ",
  async (operation) => {
    if (operation === "delete") {
      readline.question("Enter the event ID to delete: ", async (eventId) => {
        await deleteEventById(eventId);
        client.end(); // Zamknięcie połączenia po wykonaniu operacji
        readline.close();
      });
    } else if (operation === "update") {
      readline.question(
        "Enter the event ID to update date: ",
        async (eventId) => {
          readline.question(
            "Enter the new date (YYYY-MM-DD): ",
            async (newDate) => {
              await updateEventDateById(eventId, newDate);
              client.end(); // Zamknięcie połączenia po wykonaniu operacji
              readline.close();
            },
          );
        },
      );
    } else {
      console.log('Invalid operation. Please enter "delete" or "update".');
      client.end(); // Zamknięcie połączenia w przypadku nieprawidłowej operacji
      readline.close();
    }
  },
);
