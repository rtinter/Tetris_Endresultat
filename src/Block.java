/**
 * Die abstrakte Block-Klasse repräsentiert einen Spielblock.
 */
public abstract class Block {

    protected int[][][] tiles;  // Darstellung des Blocks als Array von Tiles
    protected int[] currentPosition;  // Aktuelle Position des Blocks
    private int currentRotation = 0;  // Aktuelle Rotation des Blocks
    private GameGrid _gameGrid;  // Spielfeld, auf dem sich der Block befindet


    /**
     * Konstruktor für die Block-Klasse.
     * @param grid Das Spielfeld, auf dem sich der Block befindet.
     */
    public Block(GameGrid grid) {
        tiles = createTiles();
        currentPosition = getStartCoords();
        _gameGrid = grid;
    }


    /**
     * Abstrakte Methode um die Tiles des Blocks zu erzeugen.
     */
    protected abstract int[][][] createTiles();


    /**
     * Abstrakte Methode um die ID des Blocks zu erhalten.
     */
    protected abstract int getId();


    /**
     * Gibt die aktuelle Rotation des Blocks zurück.
     * @return Die aktuelle Rotation des Blocks.
     */
    public int getCurrentRotation() {
        return currentRotation;
    }


    /**
     * Abstrakte Methode um die Startkoordinaten des Blocks zu bestimmen.
     */

    protected abstract int[] getStartCoords();

    /**
     * Gibt ein dreidimensionales Array zurück, das die Tiles des Blocks repräsentiert.
     * @return Ein dreidimensionales Array der Tiles.
     */
    public int[][][] getTiles() {
        return tiles;
    }


    /**
     * Setzt die Tiles des Blocks auf ein neues dreidimensionales Array.
     * @param newTiles Das neue dreidimensionale Array der Tiles.
     */
    public void setTiles(int[][][] newTiles) {
        tiles = newTiles;
    }


    /**
     * Gibt die aktuelle Position des Blocks zurück.
     * @return Ein Array, das die aktuelle Position des Blocks repräsentiert.
     */
    public int[] getCurrentPosition() {
        return currentPosition;
    }


    /**
     * Setzt die aktuelle Position des Blocks auf die angegebenen Koordinaten.
     * @param newCoordinates Ein Array, das die neuen Koordinaten repräsentiert.
     */
    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }


    /**
     * Gibt die Startkoordinaten für den nächsten Block zurück.
     *
     * @return Ein Array von zwei Ganzzahlen, das die Startkoordinaten für den nächsten Block repräsentiert.
     */
    protected int[] startCoordsForNextBlock() {
        int[] coords = {1, 0};
        return coords;
    }


    /**
     * Versucht, den Block nach unten zu bewegen und gibt zurück, ob dies erfolgreich war.
     * @return true, wenn der Block erfolgreich nach unten bewegt wurde, sonst false.
     */
    public boolean moveDown() {
        int[] currentPosition = getCurrentPosition();

        int[] newPosition = {currentPosition[0] + 1, currentPosition[1]};

        if (canBlockFit(newPosition)) {
            currentPosition[0]++;
            return true;
        } else {
            return false;
        }
    }


    /**
     * Versucht den Block nach links zu bewegen und gibt zurück, ob dies erfolgreich war.
     * @return true, wenn der Block erfolgreich nach links bewegt wurde, sonst false.
     */
    public boolean moveLeft() {
        int[] currentPosition = getCurrentPosition();

        int[] newPosition = {currentPosition[0], currentPosition[1] - 1};

        if (canBlockFit(newPosition)) {
            currentPosition[1]--;
            return true;
        } else {
            return false;
        }
    }


    /**
     * Versucht den Block nach rechts zu bewegen und gibt zurück, ob dies erfolgreich war.
     * @return true, wenn der Block erfolgreich nach rechts bewegt wurde, sonst false.
     */
    public boolean moveRight() {
        int[] currentPosition = getCurrentPosition();

        int[] newPosition = {currentPosition[0], currentPosition[1] + 1};

        if (canBlockFit(newPosition)) {
            currentPosition[1]++;
            return true;
        } else {
            return false;
        }
    }


    /**
     * Versucht den Block zu drehen und gibt zurück, ob dies erfolgreich war.
     * @return true, wenn der Block erfolgreich gedreht wurde, sonst false.
     */
    public boolean rotate() {
        int[][][] newTiles = tiles.clone();
        int nextRotation = (currentRotation + 1) % newTiles.length;

        int[] newPosition = currentPosition.clone();

        if (!isRotationOutOfBounds(newTiles, newPosition, _gameGrid, nextRotation)) {
            if (canBlockFit(newPosition)) {
                setTiles(newTiles);
                currentRotation = nextRotation;
                return true;
            }
        }
        return false;
    }


    /**
     * Überprüft, ob eine bestimmte Rotation des Blocks außerhalb des Spielfelds liegen würde.
     *
     * @param tiles Das dreidimensionale Array, das die Tiles des Blocks repräsentiert.
     * @param position Die Position des Blocks.
     * @param gameGrid Das Spielfeld.
     * @param nextRotation Die nächste Rotation des Blocks.
     * @return true, wenn die Rotation des Blocks außerhalb des Spielfelds liegen würde, sonst false.
     */
    private boolean isRotationOutOfBounds(int[][][] tiles, int[] position, GameGrid gameGrid, int nextRotation) {
        for (int i = 0; i < tiles[nextRotation].length; i++) {
            int row = tiles[nextRotation][i][0] + position[0];
            int col = tiles[nextRotation][i][1] + position[1];

            if (row < 0 || col < 0 || row >= gameGrid.getRows() || col >= gameGrid.getCols()) {
                return true;
            }
        }
        return false;
    }


    /**
     * Überprüft, ob der Block an einer bestimmten Position platziert werden kann.
     * @param position Die Position, die überprüft werden soll.
     * @return true, wenn der Block an der Position platziert werden kann, sonst false.
     */
    public boolean canBlockFit(int[] position) {
        var tiles = this.getTiles();
        for (int i = 0; i < tiles[currentRotation].length; i++) {
            int row = tiles[currentRotation][i][0] + position[0];
            int col = tiles[currentRotation][i][1] + position[1];

            // Kollisionserkennung mit den Seiten des Spielfelds
            if (row < 0 || col < 0 || row >= _gameGrid.getRows() || col >= _gameGrid.getCols() || !_gameGrid.IsEmpty(row, col)) {
                return false;
            }
        }
        return true;
    }


    /**
     * "Friert" den Block ein, wird unveränderlich und kann nicht mehr bewegt werden.
     * @param gameGrid Das Spielfeld, auf dem der Block eingefroren werden soll.
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