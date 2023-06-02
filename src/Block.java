public abstract class Block {
    protected int[][][] tiles;

    public Block() {
        tiles = createTiles();
    }

    protected abstract int[][][] createTiles();

    public abstract int getId();

    protected abstract int[] getStartOffset();

    public int[][][] getTiles() {
        return tiles;
    }

    public void moveDown() {
        int[] startOffset = getStartOffset();
        startOffset[0]++; // Erhöhen Sie den Startoffset für die Y-Koordinate, um den Block nach unten zu bewegen
    }


}