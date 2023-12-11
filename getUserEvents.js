//To dopiero będzie przydatne kiedy użytkownicy będą zapisani, nie polecam dodawania sztucznie, bo w związku z relacjami w bazie przestaje wtedy działac paneladmina i rezerwacje
/*const { Client } = require('pg');

const client = new Client({
    host: "localhost",
    user: "postgres",
    port: 5432,
    password: "123Ania123",
    database: "iodata"
});

client.connect();

// Funkcja pobierająca wszystkie wydarzenia dla danego uczestnika o podanym ID
const getEventsByUserId = async (userId) => {
    try {
const findEventsQuery = 'SELECT events.* FROM events_users JOIN events ON events_users.event_id = events.id WHERE events_users.users_id = $1';
        const eventsResult = await client.query(findEventsQuery, [userId]);

        if (eventsResult.rows.length > 0) {
            console.log(`Events for user with ID ${userId}:`);
            console.log(eventsResult.rows);
        } else {
            console.log(`No events found for user with ID ${userId}.`);
        }
    } catch (error) {
        console.error('Error getting events by user ID:', error.message);
    }
};

// Odczyt ID uczestnika z konsoli
const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
});

readline.question('Enter the user ID to retrieve events: ', async (userId) => {
    await getEventsByUserId(userId);
    client.end(); 
    readline.close();
});
*/