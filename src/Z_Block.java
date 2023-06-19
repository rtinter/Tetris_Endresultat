/**
 * Repräsentiert den Z_Block.
 * Diese Klasse erbt von der abstrakten Klasse Block und implementiert dessen abstrakte Methoden.
 *
 * Der Z_Block hat eine eindeutige Tile-Konfiguration und eine spezifische ID.
 */
public class Z_Block extends Block {


    /**
     * Konstruktor für Z_Block.
     * Ruft den Konstruktor der übergeordneten Klasse auf.
     *
     * @param grid Das Spielraster, in dem sich der Block bewegt.
     */
    public Z_Block(GameGrid grid){ super(grid); }


    /**
     * Erstellt und gibt die spezifische Kachelkonfiguration für Z_Block zurück.
     *
     * @return Ein 3D-Array, das die spezifische Kachelkonfiguration für Z_Block darstellt.
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
     * Gibt die eindeutige ID des Z_Blocks zurück.
     *
     * @return Die eindeutige ID des Z_Blocks.
     */
    public int getId() {
        return 4;
    }


    /**
     * Gibt die Startkoordinaten des Z_Blocks zurück.
     *
     * @return Ein Array von zwei Ganzzahlen, das die Startkoordinaten des Z_Blocks repräsentiert.
     */
    public int[] startCoords() {
        return new int[] { 0, 5 };
    }


    /**
     * Setzt die aktuellen Koordinaten des Z_Blocks auf die angegebenen neuen Koordinaten.
     *
     * @param newCoordinates Die neuen Koordinaten, auf die der Z_Block gesetzt werden soll.
     */
    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }


    /**
     * Gibt die Tiles des Z_Blocks zurück.
     *
     * @return Ein 3D-Array, das die Tiles des Z_Blocks repräsentiert.
     */
    public int[][][] getTiles() {
        return tiles;
    }
}