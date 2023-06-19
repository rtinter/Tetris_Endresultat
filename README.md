Tetris in Java

Dieses Projekt ist eine Java-Implementierung des Spieleklassikers Tetris. 
Es wurde entwickelt, um die Grundlagen der objektorientierten Programmierung in Java zu lernen.

**Spielregeln**

Das Ziel des Spiels ist es, Reihen mit Tetrominos (Formen aus je vier Blöcken) zu füllen, um sie vom Bildschirm zu entfernen.
Die Tetrominos fallen vom oberen Bildschirmrand herunter und können vom Spieler mithilfe der Pfeiltasten nach links und rechts bewegt und gedreht werden.
Wenn eine horizontale Reihe vollständig mit Tetrominos gefüllt ist, wird sie vom Bildschirm entfernt, und der Spieler erhält Punkte.
Das Spiel endet, wenn die Tetrominos den oberen Bildschirmrand erreichen.
___
**Funktionalität**

Das Tetris-Spiel verfügt über folgende Funktionen:

- Anzeige des Spielfelds und des aktuellen Tetrominos
- Steuerung des Tetrominos durch den Spieler (Bewegung nach links/rechts, Drehung)
- Erkennung und Entfernung vollständiger Reihen
- Punktezählung und Anzeige des Spielstands
- Anzeige des nächsten Tetrominos
- Spielende-Erkennung
- Start und Pause
___

**_Logik_**


_**Spielfeld**:_
* Das Spielfeld wird als 2D-Array dargestellt und besteht aus Reihen und Spalten. Jede Zelle dieses Spielfelds 
hat anfangs den Wert 0.

**_Tetrominos_**:
* Die Grundlogik der Tetrominos befindet sich in der Block-Klasse. Von dieser Klasse erben die spezifischen Blöcke 
die meisten ihrer Eigenschaften. Die Blöcke selbst werden als 3D-Array gespeichert, wobei die erste Dimension die
Rotation, die zweite Dimension die vier Positionen der einzelnen Blöcke und die dritte Dimension die Zeile und 
Spalte des jeweiligen Blocks angibt. Durch diese Speicherung wird die Realisierung der Rotation, Bewegung oder 
das Hinzufügen/Ändern neuer Blöcke vereinfacht. Jeder Block erhält eine eindeutige ID.

**BlockFactory:**
* Hier handelt es sich um ein Singleton, da sie nur einmal im Spiel existiert. Sie speichert einen Block in der 
* Block-Warteschlange (BlockQueue), welcher dann als aktueller Block verwendet wird. Anschließend wird der nächsten
Block in der Warteschlange zugewiesen. Dadurch wird eine Warteschlange erstellt und der nächste Block wird 
erfolgreich im Spiel angezeigt.

**Wie wird nun ein Block auf das Spielfeld gezeichnet?**
* Das Spielfeld besteht aus Nullen. Der aktuelle Block enthält den Block aus der Block-Warteschlange der
BlockFactory, und der Block in der Warteschlange wird durch einen neuen Block ersetzt. Die Blöcke werden 
auf den Feldern durch ihre ID repräsentiert. Jede ID hat eine andere Farbe, und die Zellen werden entsprechend 
der ID eingefärbt.

**Bewegung der Blöcke:**
* Aufgrund der Repräsentation durch die IDs muss ein Tetromino nur überprüfen, ob das Feld, auf das er 
sich als nächstes bewegen möchte, größer als 0 ist. Dafür muss der Tetromino sich zuerst selbst löschen, 
da er sich sonst möglicherweise selbst wahrnimmt. Wenn das Feld, auf das er sich bewegen soll, größer 
als 0 ist, wird die Freeze-Methode aktiviert. Die ID des Blocks wird im GameGrid festgelegt, und der 
Malzyklus des nächsten Blocks beginnt.

**Löschen von Reihen:**
* Für diese Funktion ist die Klasse GridController zuständig. Sie überprüft, ob eine Zeile vollständig 
ist, d. h. jede Zelle in der Zeile einen Wert größer als 0 hat. Wenn dies der Fall ist, wird die 
Zeile gelöscht, und ein Zählerwert wird um 1 erhöht. Abhängig von diesem Zählerwert wird der 
Punktestand um einen bestimmten Wert erhöht, die oberen Reihen um den entsprechenden Wert nach 
unten verschoben und neue leere Zeilen oben hinzugefügt.

**GameState:**
* Das Spiel enthält 4 GameStates, die durch eine ENUM realisiert wurden: Start, Running, Pause und Game Over.
Je nach GameState wird eine andere Szene ausgeführt, die dann in der draw-Funktion aufgerufen wird.
Diese Szenen sind in der Main-Methode implementiert.

**Weitere Erläuterungen:**
* Wir haben versucht, die in der Aufgabenstellung geforderte Vererbungstiefe von 3 zu erreichen, sind jedoch auf Probleme
gestoßen. Die zusätzlichen Vererbungen wären trivial gewesen und hätten einen negativen Effekt auf
das Spiel und seine Lesbarkeit gehabt. Daher haben wir uns entschieden, die aktuellen Klassenstruktur beizubehalten.

**Warum gibt es zwei "drawBlock"-Methoden? (drawBlock und drawNextBlock)**
* Um sicherzustellen, dass ein Tetromino in der Mitte des Spielfelds oben angezeigt wird, benötigt es Startkoordinaten,
die an die "drawBlock"-Methode übergeben werden. Das Feld für den "NextBlock" ist jedoch nur 4x4 groß
und daher zu klein für den nächsten Block. Dadurch würde ein Fehler auftreten. Daher haben wir eine Methode
namens "getStartCoordsForNextBlock" in der Block-Klasse implementiert. Diese Methode gibt spezifische
Startkoordinaten zurück, die für die erfolgreiche Darstellung des nächsten
Blocks im 4x4 Spielfeld verwendet werden können.

___

**_Steuerung_**

- Spiel starten: Enter-Taste
- Spiel pausieren: Leertaste
- Tetromino nach links/rechts bewegen: Pfeiltasten links/rechts
- Tetromino rotation Pfeiltaste hoch
- Tetromino schneller nach unten bewegen: Pfeiltaste runter 

Autoren

René Tinter, Tim Greis, Philip Schröder
