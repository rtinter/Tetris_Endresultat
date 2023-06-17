
public class I_Block extends Block {

    public I_Block(GameGrid grid){ super(grid); }

    protected int[][][] createTiles() {
        int[][][] tiles = new int[4][4][2];
        tiles[0] = new int[][]{{1, 0}, {1, 1}, {1, 2}, {1, 3}};
        tiles[1] = new int[][]{{0, 2}, {1, 2}, {2, 2}, {3, 2}};
        tiles[2] = new int[][]{{2, 0}, {2, 1}, {2, 2}, {2, 3}};
        tiles[3] = new int[][]{{0, 1}, {1, 1}, {2, 1}, {3, 1}};
        return tiles;
    }

    public int getId() {
        return 1;
    }

    public int[] startCoords() {
        return new int[]{-1, 3};
    }

    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }

    public int[][][] getTiles() {
        return tiles;
    }
}