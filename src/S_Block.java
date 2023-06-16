/**
 * Die Klasse S_Block erweitert die Block-Klasse und definiert die Eigenschaften und Methoden
 * für den S-förmigen Block im Tetris-Spiel.
 */
public class S_Block extends Block {

    public S_Block(GameGrid grid){ super(grid); }

    /**
     * Erstellt die Tile-Konfigurationen für den S-förmigen Block in verschiedenen Rotationen.
     *
     * @return Die Tile-Konfigurationen für den Block.
     */
    protected int[][][] createTiles() {
        int[][][] tiles = new int[4][4][2]; // 1. Dimension = Rotationstate, 2. Dimension = Position der Tiles im Objekt, 3. Dimension = x- und y-Koordinaten
        tiles[0] = new int[][] { { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 } };
        tiles[1] = new int[][] { { 0, 1 }, { 1, 1 }, { 1, 2 }, { 2, 2 } };
        tiles[2] = new int[][] { { 1, 1 }, { 1, 2 }, { 2, 0 }, { 2, 1 } };
        tiles[3] = new int[][] { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 } };
        return tiles;
    }

    /**
     * Gibt die ID des S-Blocks zurück.
     *
     * @return Die ID des Blocks.
     */
    public int getId() {
        return 2;
    }

    /**
     * Gibt die Startkoordinaten des S-Blocks zurück.
     *
     * @return Die Startkoordinaten des Blocks.
     */
    public int[] startCoords() {
        return new int[] { 0, 5 };
    }

    /**
     * Setzt die aktuellen Koordinaten des S-Blocks.
     *
     * @param newCoordinates Die neuen Koordinaten des Blocks.
     */
    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }

    /**
     * Gibt die Tile-Konfigurationen des S-Blocks zurück.
     *
     * @return Die Tile-Konfigurationen des Blocks.
     */
    public int[][][] getTiles() {
        return tiles;
    }
}