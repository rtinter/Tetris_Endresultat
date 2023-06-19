/**
 * Repräsentiert den I_Block im GameGrid.
 */
public class I_Block extends Block {

    /**
     * Erstellt einen I_Block.
     *
     * @param grid Das Spielgitter (GameGrid)
     */
    public I_Block(GameGrid grid){ super(grid); }

    /**
     * Erstellt die Form für den I_Block.
     *
     * @return Ein 3D-Array, das die Formen für den I_Block in verschiedenen Drehpositionen repräsentiert
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
     * Gibt die ID des I_Block zurück.
     *
     * @return Die ID des I_Block
     */
    public int getId() {
        return 1;
    }

    /**
     * Gibt die Startkoordinaten für den I_Block zurück.
     *
     * @return Ein Array, das die Startkoordinaten für den I_Block enthält
     */
    public int[] startCoords() {
        return new int[]{-1, 3};
    }

    /**
     * Setzt die Koordinaten des I_Block auf neue Koordinaten.
     *
     * @param newCoordinates Ein Array, das die neuen Koordinaten enthält
     */
    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }

    /**
     * Gibt die Tiles des I_Block zurück.
     *
     * @return Ein 3D-Array, das die Tiles des I_Block repräsentiert
     */
    public int[][][] getTiles() {
        return tiles;
    }
}