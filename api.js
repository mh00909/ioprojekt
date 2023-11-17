
import axios from 'axios';

// Utwórz instancję Axios z konfiguracją domyślną
const api = axios.create({
  baseURL: 'http://localhost:8080', // Adres URL twojego backendu
  withCredentials: true
});

export default api;