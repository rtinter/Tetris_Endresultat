/**
 * Die Klasse J_Block repräsentiert den J-förmigen Tetris-Block im Spiel.
 * Die Positionen und Rotationen des Blocks werden definiert und verwaltet.
 */
public class J_Block extends Block {

    private final int[][][] tiles = createTiles(); // Die Positionen der Tiles des Blocks
    public int[] currentPosition; // Die aktuelle Position des Blocks

    /**
     * Erstellt die Positionen der Tiles für den J-Block in verschiedenen Rotationen.
     *
     * @return Die Positionen der Tiles für den J-Block.
     */
    protected int[][][] createTiles() {
        int[][][] tiles = new int[4][4][2];
        tiles[0] = new int[][]{{0, 0}, {1, 0}, {1, 1}, {1, 2}};
        tiles[1] = new int[][]{{0, 1}, {0, 2}, {1, 1}, {2, 1}};
        tiles[2] = new int[][]{{1, 0}, {1, 1}, {1, 2}, {2, 2}};
        tiles[3] = new int[][]{{0, 1}, {1, 1}, {2, 1}, {2, 0}};
        return tiles;
    }

    /**
     * Gibt die ID des J-Blocks zurück.
     *
     * @return Die ID des J-Blocks.
     */
    public int getId() {
        return 5;
    }

    /**
     * Gibt die Startkoordinaten des J-Blocks zurück.
     *
     * @return Die Startkoordinaten des J-Blocks als Array [row, col].
     */
    public int[] startCoords() {
        return new int[]{0, 4};
    }

    /**
     * Setzt die aktuellen Koordinaten des J-Blocks.
     *
     * @param newCoordinates Die neuen Koordinaten des J-Blocks als Array [row, col].
     */
    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }

    /**
     * Gibt die Positionen der Tiles des J-Blocks zurück.
     *
     * @return Die Positionen der Tiles des J-Blocks.
     */
    public int[][][] getTiles() {
        return tiles;
    }
}