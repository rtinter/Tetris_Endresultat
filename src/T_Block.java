/**
 * Repräsentiert den T_Block.
 * Diese Klasse erbt von der abstrakten Klasse Block und implementiert dessen abstrakte Methoden.
 *
 * Der T_Block hat eine eindeutige Tile-Konfiguration und eine spezifische ID.
 */
public class T_Block extends Block {


    /**
     * Konstruktor für T_Block.
     * Ruft den Konstruktor der übergeordneten Klasse auf.
     *
     * @param grid Das Spielraster, in dem sich der Block bewegt.
     */
    public T_Block(GameGrid grid){ super(grid); }


    /**
     * Erstellt und gibt die spezifische Kachelkonfiguration für T_Block zurück.
     *
     * @return Ein 3D-Array, das die spezifische Kachelkonfiguration für T_Block darstellt.
     */
    protected int[][][] createTiles() {
        int[][][] tiles = new int[4][4][2]; // 1. Dimension = Rotationstate, 2. Dimension = Position der Tiles im Objekt, 3. Dimension = x- und y-Koordinaten
        tiles[0] = new int[][] { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 1, 2 } };
        tiles[1] = new int[][] { { 0, 1 }, { 1, 1 }, { 1, 2 }, { 2, 1 } };
        tiles[2] = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 1 } };
        tiles[3] = new int[][] { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 2, 1 } };
        return tiles;
    }


    /**
     * Gibt die eindeutige ID des T_Blocks zurück.
     *
     * @return Die eindeutige ID des T_Blocks.
     */
    public int getId() {
        return 3;
    }


    /**
     * Gibt die Startkoordinaten des T_Blocks zurück.
     *
     * @return Ein Array von zwei Ganzzahlen, das die Startkoordinaten des T_Blocks repräsentiert.
     */
    private int[] startCoords() {
        return new int[] { 0, 5 };
    }

    public int[] getStartCoords(){
        return startCoords();
    }

    /**
     * Setzt die aktuellen Koordinaten des T_Blocks auf die angegebenen neuen Koordinaten.
     *
     * @param newCoordinates Die neuen Koordinaten, auf die der T_Block gesetzt werden soll.
     */
    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }


    /**
     * Gibt die Tiles des T_Blocks zurück.
     *
     * @return Ein 3D-Array, das die Tiles des T_Blocks repräsentiert.
     */
    public int[][][] getTiles() {
        return tiles;
    }
}