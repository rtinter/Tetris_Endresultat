/**
 * Repräsentiert den L_Block.
 * Diese Klasse erbt von der abstrakten Klasse Block und implementiert dessen abstrakte Methoden.
 *
 * Der L_Block hat eine eindeutige Tile-Konfiguration und eine spezifische ID.
 */
public class L_Block extends Block {


    /**
     * Konstruktor für L_Block.
     * Ruft den Konstruktor der übergeordneten Klasse auf.
     *
     * @param grid Das Spielraster, in dem sich der Block bewegt.
     */
    public L_Block(GameGrid grid){ super(grid); }


    /**
     * Erstellt und gibt die spezifische Kachelkonfiguration für L_Block zurück.
     *
     * @return Ein 3D-Array, das die spezifische Kachelkonfiguration für L_Block darstellt.
     */
    protected int[][][] createTiles() {
        int[][][] tiles = new int[4][4][2]; // 1. Dimension = Rotationstate, 2. Dimension = Position der Tiles im Objekt, 3. Dimension = x- und y-Koordinaten
        tiles[0] = new int[][] { { 0, 2 }, { 1, 0 }, { 1, 1 }, { 1, 2 } };
        tiles[1] = new int[][] { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 2, 2 } };
        tiles[2] = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 0 } };
        tiles[3] = new int[][] { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 2, 1 } };
        return tiles;
    }


    /**
     * Gibt die eindeutige ID des J_Blocks zurück.
     *
     * @return Die eindeutige ID des J_Blocks.
     */
    public int getId() {
        return 7;
    }


    /**
     * Gibt die Startkoordinaten des L_Blocks zurück.
     *
     * @return Ein Array von zwei Ganzzahlen, das die Startkoordinaten des L_Blocks repräsentiert.
     */
    public int[] startCoords() {
        return new int[] { 0, 4 };
    }


    /**
     * Setzt die aktuellen Koordinaten des L_Blocks auf die angegebenen neuen Koordinaten.
     *
     * @param newCoordinates Die neuen Koordinaten, auf die der L_Block gesetzt werden soll.
     */
    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }


    /**
     * Gibt die Tiles des L_Blocks zurück.
     *
     * @return Ein 3D-Array, das die Tiles des L_Blocks repräsentiert.
     */
    public int[][][] getTiles() {
        return tiles;
    }
}