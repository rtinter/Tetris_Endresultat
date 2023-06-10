/**
 * Die Klasse O_Block erweitert die Block-Klasse und definiert die Eigenschaften und Methoden
 * für den O-förmigen Block im Tetris-Spiel.
 */
public class O_Block extends Block {

    private final int[][][] tiles = createTiles();

    /**
     * Erstellt die Tile-Konfigurationen für den O-förmigen Block in verschiedenen Rotationen.
     *
     * @return Die Tile-Konfigurationen für den Block.
     */
    protected int[][][] createTiles() {
        int[][][] tiles = new int[4][4][2]; // 1. Dimension = Rotationstate, 2. Dimension = Position der Tiles im Objekt, 3. Dimension = x- und y-Koordinaten
        tiles[0] = new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
        return tiles;
    }

    /**
     * Rotiert den O-Block. Da der O-Block nicht rotiert werden kann, wird immer "false" zurückgegeben.
     *
     * @param gameGrid Das Spielraster, auf dem der Block platziert ist.
     * @return False, da der O-Block nicht rotiert werden kann.
     */
    @Override
    public boolean rotate(GameGrid gameGrid) {
        // O-Blöcke dürfen nicht rotieren, daher wird immer "false" zurückgegeben
        return false;
    }

    /**
     * Gibt die ID des O-Blocks zurück.
     *
     * @return Die ID des Blocks.
     */
    public int getId() {
        return 6;
    }

    /**
     * Gibt die Startkoordinaten des O-Blocks zurück.
     *
     * @return Die Startkoordinaten des Blocks.
     */
    public int[] startCoords() {
        return new int[] { 1, 4 };
    }

    /**
     * Setzt die aktuellen Koordinaten des O-Blocks.
     *
     * @param newCoordinates Die neuen Koordinaten des Blocks.
     */
    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }

    /**
     * Gibt die Tile-Konfigurationen des O-Blocks zurück.
     *
     * @return Die Tile-Konfigurationen des Blocks.
     */
    public int[][][] getTiles() {
        return tiles;
    }
}