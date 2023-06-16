/**
 * Die Klasse Z_Block erweitert die Block-Klasse und definiert die Eigenschaften und Methoden
 * für den Z-förmigen Block im Tetris-Spiel.
 */
public class Z_Block extends Block {

    public Z_Block(GameGrid grid){ super(grid); }

    /**
     * Erstellt die Tile-Konfigurationen für den Z-förmigen Block in verschiedenen Rotationen.
     *
     * @return Die Tile-Konfigurationen für den Block.
     */
    protected int[][][] createTiles() {
        int[][][] tiles = new int[4][4][2]; // 1. Dimension = Rotationstate, 2. Dimension = Position der Tiles im Objekt, 3. Dimension = x- und y-Koordinaten
        tiles[0] = new int[][] { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 } };
        tiles[1] = new int[][] { { 0, 2 }, { 1, 1 }, { 1, 2 }, { 2, 1 } };
        tiles[2] = new int[][] { { 1, 0 }, { 1, 1 }, { 2, 1 }, { 2, 2 } };
        tiles[3] = new int[][] { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 2, 0 } };
        return tiles;
    }

    /**
     * Gibt die ID des Z-Blocks zurück.
     *
     * @return Die ID des Blocks.
     */
    public int getId() {
        return 4;
    }

    /**
     * Gibt die Startkoordinaten des Z-Blocks zurück.
     *
     * @return Die Startkoordinaten des Blocks.
     */
    public int[] startCoords() {
        return new int[] { 0, 5 };
    }

    /**
     * Setzt die aktuellen Koordinaten des Z-Blocks.
     *
     * @param newCoordinates Die neuen Koordinaten des Blocks.
     */
    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }

    /**
     * Gibt die Tile-Konfigurationen des Z-Blocks zurück.
     *
     * @return Die Tile-Konfigurationen des Blocks.
     */
    public int[][][] getTiles() {
        return tiles;
    }
}