#### Use case: Ich als BenutzerIn möchte ein Fährticket buchen

**Kurze Beschreibung**:
Ein Benutzer benutzt entweder über eine WebApp oder eine MobileApp um ein Fährticket zu buchen.
Bevor der User zur Buchungsansicht kommt wird sichergestellt das der User eingeloggt ist und nach eingabe und überweisung der Daten werden die Daten auf Validität überprüft.
Ziel ist es das ein Ticket erfolgreich zu buchen und den Benutzer über den erfolgreichen Eingang der Buchung hinzuweisen.

**Akteur**
Eingeloggter Benutzer

**Auslöser**
User bucht ein Fährticket über die WebApp oder MobileApp

**Vorbedingung**
User ist registriert und ist eingeloggt. Dienst, sowie DBMS sind online und reagieren.

**Standarablauf**
1:	User Klickt auf Weiterleitung zur Buchungsseite
2:	User wird zur Buchungsansicht weitergeleitet
3:	User kann als Gast buchen
4:	User kann bereits eingeloggt buchen
5:	User nutzt das UI um Eingaben zu tätigen
6:  User wählt die Fahrt und Kabine
7:	User kann nach Preis und Kabinentyp filtern
8:	User drückt nach wahl auf weiter button am ende der seite
9:	Gast Benutzer müssen ihren Vornamen, Nachnamen, Email-Adresse, Adresse(Straße, Hausnummer, Stadt, PLZ), Bezahlart wählen
10:	Eingeloggter Benutzer müssen die Bezhlart wählen. Die anderen Daten werden von der Datenbank gerufen
11:	Email wird redumentär im Frontend validiert
12: User drückt auf weiter button
13:	User kann Kabinen nach Preis und Kabinentyp filtern
14:	User bestätigt eingaben über submit button am Ende
