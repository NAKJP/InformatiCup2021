## spe_ed informatiCup2021 - Team: sNAKez
**Table of Content:**
1. [Beschreibung](#description)
1. [Build docker](#build-docker)
1. [Run docker](#run-docker)
1. [TODO](#TODO)

### Beschreibung <a name="description"></a>
Im Zuge des InformatiCup2021 wurde die folgende Aufgabe gestellt: Programmiere einen Spieler,
der eigenständig das Spiel spe_ed spielen kann. 
<br><br>
Mittels Websocket wird mit einem Server kommuniziert. Als Spieler erhält man eine Nachricht (JSON), welche zuerst gemappt wird. 
Weiterhin wird geprüft, welche Spielernr. unser Spieler hat. Der Server erwartet eine Antwort, welchen Schritt der Spieler als nächstes machen wird.
<br><br>
Ein Spielzug wird wie folgt ermittelt: Es wird geprüft, ob das Feld vor, links und rechts besetzt sind. Sind diese frei, 
wird der jeweilige Spielzug in eine Liste möglicher Spielzüge hinzugefügt. Es wird ein zufälliger Spielzug aus den möglichen Spielzügen (pro Runde) an den Server zurückgeschickt.

### Build docker <a name="build-docker"></a>
1. Download .zip vom GitHub Repo (https://github.com/NAKJP/InformatiCup2021)
2. Entpacke .zip
3. `docker build -t snakez .<Pfad in dem entpackte zip liegt z.B. /downloads/<Name der entpackten zip>>`

### Run docker <a name="run-docker"></a>
`docker run -e URL="wss://msoll.de/spe_ed" -e KEY="<TEAM-KEY>" -e TIME_URL="https://msoll.de/spe_ed_time" snakez`

### TODO <a name="TODO"></a>
1. Fehlerbehandlung?
