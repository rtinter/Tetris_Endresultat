/**
 * Repräsentiert den I_Block.
 * Diese Klasse erbt von der abstrakten Klasse Block und implementiert dessen abstrakte Methoden.
 *
 * Der I_Block hat eine eindeutige Tile-Konfiguration und eine spezifische ID.
 */
public class I_Block extends Block {

    /**
     * Konstruktor für I_Block.
     * Ruft den Konstruktor der übergeordneten Klasse auf.
     *
     * @param grid Das Spielraster, in dem sich der Block bewegt.
     */
    public I_Block(GameGrid grid){ super(grid); }


    /**
     * Erstellt und gibt die spezifische Kachelkonfiguration für I_Block zurück.
     *
     * @return Ein 3D-Array, das die spezifische Kachelkonfiguration für J_Block darstellt.
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
     * Gibt die eindeutige ID des I_Block zurück.
     *
     * @return Die eindeutige ID des I_Block
     */
    public int getId() {
        return 1;
    }


    /**
     * Gibt die Startkoordinaten des I_Blocks zurück.
     *
     * @return Ein Array von zwei Ganzzahlen, das die Startkoordinaten des I_Blocks repräsentiert.
     */
    public int[] startCoords() {
        return new int[]{-1, 3};
    }


    /**
     * Setzt die aktuellen Koordinaten des I_Blocks auf die angegebenen neuen Koordinaten.
     *
     * @param newCoordinates Die neuen Koordinaten, auf die der J_Block gesetzt werden soll.
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