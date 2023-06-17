/**
 * Die Klasse T_Block erweitert die Block-Klasse und definiert die Eigenschaften und Methoden
 * für den T-förmigen Block im Tetris-Spiel.
 */
public class T_Block extends Block {

    public T_Block(GameGrid grid){ super(grid); }

    /**
     * Erstellt die Tile-Konfigurationen für den T-förmigen Block in verschiedenen Rotationen.
     *
     * @return Die Tile-Konfigurationen für den Block.
     */

    public int[][][] createTiles() {
        int[][][] tiles = new int[4][4][2]; // 1. Dimension = Rotationstate, 2. Dimension = Position der Tiles im Objekt, 3. Dimension = x- und y-Koordinaten
        tiles[0] = new int[][] { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 1, 2 } };
        tiles[1] = new int[][] { { 0, 1 }, { 1, 1 }, { 1, 2 }, { 2, 1 } };
        tiles[2] = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 1 } };
        tiles[3] = new int[][] { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 2, 1 } };
        return tiles;
    }

    /**
     * Gibt die ID des T-Blocks zurück.
     *
     * @return Die ID des Blocks.
     */
    public int getId() {
        return 3;
    }

    /**
     * Gibt die Startkoordinaten des T-Blocks zurück.
     *
     * @return Die Startkoordinaten des Blocks.
     */
    public int[] startCoords() {
        return new int[] { 0, 5 };
    }

    /**
     * Setzt die aktuellen Koordinaten des T-Blocks.
     *
     * @param newCoordinates Die neuen Koordinaten des Blocks.
     */
    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }

    /**
     * Gibt die Tile-Konfigurationen des T-Blocks zurück.
     *
     * @return Die Tile-Konfigurationen des Blocks.
     */
    public int[][][] getTiles() {
        return tiles;
    }
}