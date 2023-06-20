/**
 * Repräsentiert den O_Block.
 * Diese Klasse erbt von der abstrakten Klasse Block und implementiert dessen abstrakte Methoden.
 *
 * Der O_Block hat eine eindeutige Kachelkonfiguration und eine spezifische ID.
 * Im Gegensatz zu anderen Blöcken kann der O_Block nicht rotiert werden.
 */
public class O_Block extends Block {


    /**
     * Konstruktor für O_Block.
     * Ruft den Konstruktor der übergeordneten Klasse auf.
     *
     * @param grid Das Spielraster, in dem sich der Block bewegt.
     */
    public O_Block(GameGrid grid){ super(grid); }


    /**
     * Erstellt und gibt die spezifische Kachelkonfiguration für O_Block zurück.
     *
     * @return Ein 3D-Array, das die spezifische Kachelkonfiguration für O_Block darstellt.
     */
    protected int[][][] createTiles() {
        int[][][] tiles = new int[4][4][2]; // 1. Dimension = Rotationstate, 2. Dimension = Position der Tiles im Objekt, 3. Dimension = x- und y-Koordinaten
        tiles[0] = new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
        return tiles;
    }


    /**
     * Versucht, den O_Block zu drehen.
     * Da O_Blocke nicht gedreht werden können, gibt diese Methode immer false zurück.
     *
     * @return false, da O_Blocke nicht gedreht werden können.
     */
    @Override
    public boolean rotate() {
        return false;
    }


    /**
     * Gibt die eindeutige ID des O_Blocks zurück.
     *
     * @return Die eindeutige ID des O_Blocks.
     */
    public int getId() {
        return 6;
    }


    /**
     * Gibt die Startkoordinaten des O_Blocks zurück.
     *
     * @return Ein Array von zwei Ganzzahlen, das die Startkoordinaten des O_Blocks repräsentiert.
     */
    private int[] startCoords() {
        return new int[] { 0, 4 };
    }

    public int[] getStartCoords(){
        return startCoords();
    }

    /**
     * Setzt die aktuellen Koordinaten des O_Blocks auf die angegebenen neuen Koordinaten.
     *
     * @param newCoordinates Die neuen Koordinaten, auf die der O_Block gesetzt werden soll.
     */
    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }


    /**
     * Gibt die Kacheln des O_Blocks zurück.
     *
     * @return Ein 3D-Array, das die Kacheln des O_Blocks repräsentiert.
     */
    public int[][][] getTiles() {
        return tiles;
    }
}