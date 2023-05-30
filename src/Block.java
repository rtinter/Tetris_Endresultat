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




}