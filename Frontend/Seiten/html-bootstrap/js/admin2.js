import { backend_host } from './env.js';

// Eine GET-Anfrage an die gewünschte URL senden
fetch(`${backend_host}/admin/user/user_type/user`)
  .then(response => response.json()) // Die Antwort als JSON parsen
  .then(data => {
    // Die Daten in HTML einfügen
    const userDataDiv = document.getElementById('userData');
    userDataDiv.innerHTML = `
      <p>ID: ${data[0].id}</p>
      <p>Vorname: ${data[0].firstname}</p>
      <p>Nachname: ${data[0].lastname}</p>
      <p>Benutzername: ${data[0].username}</p>
      <p>E-Mail: ${data[0].email}</p>
      <p>Benutzertyp: ${data[0].userType}</p>
      <p>Aktiviert: ${data[0].enabled ? 'Ja' : 'Nein'}</p>
    `;
  })
  .catch(error => {
    console.error('Fehler beim Abrufen der Daten:', error);
  });
