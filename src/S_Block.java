/**
 * Repräsentiert den S_Block.
 * Diese Klasse erbt von der abstrakten Klasse Block und implementiert dessen abstrakte Methoden.
 *
 * Der S_Block hat eine eindeutige Tile-Konfiguration und eine spezifische ID.
 */
public class S_Block extends Block {


    /**
     * Konstruktor für S_Block.
     * Ruft den Konstruktor der übergeordneten Klasse auf.
     *
     * @param grid Das Spielraster, in dem sich der Block bewegt.
     */
    public S_Block(GameGrid grid){ super(grid); }


    /**
     * Erstellt und gibt die spezifische Tile-Konfiguration für S_Block zurück.
     *
     * @return Ein 3D-Array, das die spezifische Tile-Konfiguration für S_Block darstellt.
     */
    protected int[][][] createTiles() {
        int[][][] tiles = new int[4][4][2]; // 1. Dimension = RotationState, 2. Dimension = Position der Tiles im Objekt, 3. Dimension = x- und y-Koordinaten
        tiles[0] = new int[][] { { 0, 1 }, { 0, 2 }, { 1, 0 }, { 1, 1 } };
        tiles[1] = new int[][] { { 0, 1 }, { 1, 1 }, { 1, 2 }, { 2, 2 } };
        tiles[2] = new int[][] { { 1, 1 }, { 1, 2 }, { 2, 0 }, { 2, 1 } };
        tiles[3] = new int[][] { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 } };
        return tiles;
    }


    /**
     * Gibt die eindeutige ID des S_Blocks zurück.
     *
     * @return Die eindeutige ID des S_Blocks.
     */
    public int getId() {
        return 2;
    }


    /**
     * Gibt die Startkoordinaten des S_Blocks zurück.
     *
     * @return Ein Array von zwei Ganzzahlen, das die Startkoordinaten des S_Blocks repräsentiert.
     */
    private int[] startCoords() {
        return new int[] { 0, 5 };
    }

    public int[] getStartCoords(){
        return startCoords();
    }

    /**
     * Setzt die aktuellen Koordinaten des S_Blocks auf die angegebenen neuen Koordinaten.
     *
     * @param newCoordinates Die neuen Koordinaten, auf die der S_Block gesetzt werden soll.
     */
    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }


    /**
     * Gibt die Tiles des S_Blocks zurück.
     *
     * @return Ein 3D-Array, das die Tiles des S_Blocks repräsentiert.
     */
    public int[][][] getTiles() {
        return tiles;
    }
}