/**
 * Die Klasse I_Block repräsentiert den I-förmigen Tetris-Block im Spiel.
 * Die Positionen und Rotationen des Blocks werden definiert und verwaltet.
 */
public class I_Block extends Block {

    public I_Block(GameGrid grid){ super(grid); }

    /**
     * Erstellt die Positionen der Tiles für den I-Block in verschiedenen Rotationen.
     *
     * @return Die Positionen der Tiles für den I-Block.
     */
    protected int[][][] createTiles() {
        int[][][] tiles = new int[4][4][2];
        tiles[0] = new int[][]{{1, 0}, {1, 1}, {1, 2}, {1, 3}};
        tiles[1] = new int[][]{{0, 2}, {1, 2}, {2, 2}, {3, 2}};
        tiles[2] = new int[][]{{2, 0}, {2, 1}, {2, 2}, {2, 3}};
        tiles[3] = new int[][]{{0, 1}, {1, 1}, {2, 1}, {3, 1}};
        return tiles;
    }

    /**
     * Gibt die ID des I-Blocks zurück.
     *
     * @return Die ID des I-Blocks.
     */
    public int getId() {
        return 1;
    }

    /**
     * Gibt die Startkoordinaten des I-Blocks zurück.
     *
     * @return Die Startkoordinaten des I-Blocks als Array [row, col].
     */
    public int[] startCoords() {
        return new int[]{-1, 3};
    }

    /**
     * Setzt die aktuellen Koordinaten des I-Blocks.
     *
     * @param newCoordinates Die neuen Koordinaten des I-Blocks als Array [row, col].
     */
    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }

    /**
     * Gibt die Positionen der Tiles des I-Blocks zurück.
     *
     * @return Die Positionen der Tiles des I-Blocks.
     */
    public int[][][] getTiles() {
        return tiles;
    }
}