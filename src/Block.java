/**
 * Die abstrakte Klasse Block repräsentiert einen Spielblock im Tetris-Spiel.
 */
public abstract class Block {
    protected int[][][] tiles; // Die Darstellung des Blocks als Array von Tiles

    protected int[] currentPosition; // Die aktuelle Position des Blocks

    public int currentRotation = 0; // Die aktuelle Rotation des Blocks

    /**
     * Konstruktor für die Block-Klasse.
     * Initialisiert die tiles- und currentPosition-Eigenschaften.
     */
    public Block() {
        tiles = createTiles();
        currentPosition = startCoords();
    }

    /**
     * Abstrakte Methode zum Erstellen der Tiles für den Block.
     * Muss in den Unterklassen implementiert werden.
     *
     * @return Ein 3D-Array, das die Tiles des Blocks enthält.
     */
    protected abstract int[][][] createTiles();

    /**
     * Abstrakte Methode zum Abrufen der ID des Blocks.
     * Muss in den Unterklassen implementiert werden.
     *
     * @return Die ID des Blocks.
     */
    protected abstract int getId();

    /**
     * Abstrakte Methode zum Festlegen der Startkoordinaten für den Block.
     * Muss in den Unterklassen implementiert werden.
     *
     * @return Ein Array mit den Startkoordinaten des Blocks.
     */
    protected abstract int[] startCoords();

    /**
     * Gibt die Tiles des Blocks zurück.
     *
     * @return Ein 3D-Array, das die Tiles des Blocks enthält.
     */
    public int[][][] getTiles() {
        return tiles;
    }

    /**
     * Legt die Tiles des Blocks fest.
     *
     * @param newTiles Das neue Array von Tiles für den Block.
     */
    public void setTiles(int[][][] newTiles) {
        tiles = newTiles;
    }

    /**
     * Gibt die aktuelle Position des Blocks zurück.
     *
     * @return Ein Array mit den Koordinaten der aktuellen Position des Blocks.
     */
    public int[] getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Legt die Koordinaten für die aktuelle Position des Blocks fest.
     *
     * @param newCoordinates Die neuen Koordinaten für die Position des Blocks.
     */
    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }

    /**
     * Gibt die Startkoordinaten für das nextBlockArray zurück.
     * Diese Methode ist wichtig, da die x-Positionen für das Spielfeld zu groß sind.
     *
     * @return Ein Array mit den Startkoordinaten für das nextBlockArray.
     */
    protected int[] startCoordsForNextBlock() {
        int[] coords = {1, 0};
        return coords;
    }

    /**
     * Bewegt den Block nach unten, wenn möglich.
     *
     * @param gameGrid Das Spielgitter, auf dem der Block platziert ist.
     * @return true, wenn der Block erfolgreich nach unten bewegt wurde, sonst false.
     */
    public boolean moveDown(GameGrid gameGrid) {
        int[][][] tiles = getTiles();
        int[] currentPosition = getCurrentPosition();

        int[] newPosition = {currentPosition[0] + 1, currentPosition[1]};

        if (canBlockFit(tiles, newPosition, gameGrid)) {
            currentPosition[0]++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Bewegt den Block nach links, wenn möglich.
     *
     * @param gameGrid Das Spielgitter, auf dem der Block platziert ist.
     * @return true, wenn der Block erfolgreich nach links bewegt wurde, sonst false.
     */
    public boolean moveLeft(GameGrid gameGrid) {
        int[][][] tiles = getTiles();
        int[] currentPosition = getCurrentPosition();

        int[] newPosition = {currentPosition[0], currentPosition[1] - 1};

        if (canBlockFit(tiles, newPosition, gameGrid)) {
            currentPosition[1]--;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Bewegt den Block nach rechts, wenn möglich.
     *
     * @param gameGrid Das Spielgitter, auf dem der Block platziert ist.
     * @return true, wenn der Block erfolgreich nach rechts bewegt wurde, sonst false.
     */
    public boolean moveRight(GameGrid gameGrid) {
        int[][][] tiles = getTiles();
        int[] currentPosition = getCurrentPosition();

        int[] newPosition = {currentPosition[0], currentPosition[1] + 1};

        if (canBlockFit(tiles, newPosition, gameGrid)) {
            currentPosition[1]++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Dreht den Block im Uhrzeigersinn, wenn möglich.
     *
     * @param gameGrid Das Spielgitter, auf dem der Block platziert ist.
     * @return true, wenn der Block erfolgreich gedreht wurde, sonst false.
     */
    public boolean rotate(GameGrid gameGrid) {
        int[][][] newTiles = tiles.clone();
        int nextRotation = (currentRotation + 1) % newTiles.length;

        int[] newPosition = currentPosition.clone();

        // Überprüfe, ob der gedrehte Block noch innerhalb der Grenzen des Spielfelds liegt
        if (!isRotationOutOfBounds(newTiles, newPosition, gameGrid, nextRotation)) {
            if (canBlockFit(newTiles, newPosition, gameGrid)) {
                setTiles(newTiles); // Aktualisiere die tiles-Eigenschaft
                currentRotation = nextRotation;
                return true; // Rotation erfolgreich
            }
        }
        return false; // Rotation nicht möglich
    }

    /**
     * Überprüft, ob die Rotation des Blocks außerhalb der Spielfeldgrenzen liegt.
     *
     * @param tiles         Die Tiles des Blocks.
     * @param position      Die aktuelle Position des Blocks.
     * @param gameGrid      Das Spielgitter, auf dem der Block platziert ist.
     * @param nextRotation  Die nächste Rotation des Blocks.
     * @return true, wenn die Rotation außerhalb der Spielfeldgrenzen liegt, sonst false.
     */
    private boolean isRotationOutOfBounds(int[][][] tiles, int[] position, GameGrid gameGrid, int nextRotation) {
        for (int i = 0; i < tiles[nextRotation].length; i++) {
            int row = tiles[nextRotation][i][0] + position[0];
            int col = tiles[nextRotation][i][1] + position[1];

            // Überprüfen, ob der Block nach der Drehung außerhalb des Spielfelds liegt
            if (row < 0 || col < 0 || row >= gameGrid.getRows() || col >= gameGrid.getCols()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Überprüft, ob der Block an der gegebenen Position im Spielgitter platziert werden kann.
     *
     * @param tiles        Die Tiles des Blocks.
     * @param position     Die Position, an der der Block platziert werden soll.
     * @param gameGrid     Das Spielgitter, auf dem der Block platziert werden soll.
     * @return true, wenn der Block an der Position platziert werden kann, sonst false.
     */
    public boolean canBlockFit(int[][][] tiles, int[] position, GameGrid gameGrid) {
        for (int i = 0; i < tiles[currentRotation].length; i++) {
            int row = tiles[currentRotation][i][0] + position[0];
            int col = tiles[currentRotation][i][1] + position[1];

            // Kollisionserkennung mit den Seiten des Spielfelds
            if (row < 0 || col < 0 || row >= gameGrid.getRows() || col >= gameGrid.getCols() || !gameGrid.IsEmpty(row, col)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Platziert den Block im Spielgitter und "friert" ihn ein.
     *
     * @param gameGrid Das Spielgitter, auf dem der Block platziert werden soll.
     */
    public void freeze(GameGrid gameGrid) {
        int[][][] tiles = getTiles();
        int[] position = getCurrentPosition();

        for (int i = 0; i < tiles[currentRotation].length; i++) {
            int row = tiles[currentRotation][i][0] + position[0];
            int col = tiles[currentRotation][i][1] + position[1];
            gameGrid.setPosition(row, col, getId());
        }
    }
}