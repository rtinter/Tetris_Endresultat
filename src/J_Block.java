/**
 * Repräsentiert den J_Block.
 * Diese Klasse erbt von der abstrakten Klasse Block und implementiert dessen abstrakte Methoden.
 *
 * Der J_Block hat eine eindeutige Tile-Konfiguration und eine spezifische ID.
 */
public class J_Block extends Block {


    /**
     * Konstruktor für J_Block.
     * Ruft den Konstruktor der übergeordneten Klasse auf.
     *
     * @param grid Das Spielraster, in dem sich der Block bewegt.
     */
    public J_Block(GameGrid grid){ super(grid); }


    /**
     * Erstellt und gibt die spezifische Tile-Konfiguration für J_Block zurück.
     *
     * @return Ein 3D-Array, das die spezifische Tile-Konfiguration für J_Block darstellt.
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
     * Gibt die eindeutige ID des J_Blocks zurück.
     *
     * @return Die eindeutige ID des J_Blocks.
     */
    public int getId() {
        return 5;
    }


    /**
     * Gibt die Startkoordinaten des J_Blocks zurück.
     *
     * @return Ein Array von zwei Ganzzahlen, das die Startkoordinaten des J_Blocks repräsentiert.
     */
    private int[] startCoords() {
        return new int[]{0, 4};
    }

    public int[] getStartCoords(){
        return startCoords();
    }

    /**
     * Setzt die aktuellen Koordinaten des J_Blocks auf die angegebenen neuen Koordinaten.
     *
     * @param newCoordinates Die neuen Koordinaten, auf die der J_Block gesetzt werden soll.
     */
    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }


    /**
     * Gibt die Tiles des J_Blocks zurück.
     *
     * @return Ein 3D-Array, das die Tiles des J_Blocks repräsentiert.
     */
    public int[][][] getTiles() {
        return tiles;
    }
}