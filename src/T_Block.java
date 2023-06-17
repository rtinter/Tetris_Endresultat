
public class T_Block extends Block {

    public T_Block(GameGrid grid){ super(grid); }

    public int[][][] createTiles() {
        int[][][] tiles = new int[4][4][2]; // 1. Dimension = Rotationstate, 2. Dimension = Position der Tiles im Objekt, 3. Dimension = x- und y-Koordinaten
        tiles[0] = new int[][] { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 1, 2 } };
        tiles[1] = new int[][] { { 0, 1 }, { 1, 1 }, { 1, 2 }, { 2, 1 } };
        tiles[2] = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 1 } };
        tiles[3] = new int[][] { { 0, 1 }, { 1, 0 }, { 1, 1 }, { 2, 1 } };
        return tiles;
    }

    public int getId() {
        return 3;
    }

    public int[] startCoords() {
        return new int[] { 0, 5 };
    }

    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }

    public int[][][] getTiles() {
        return tiles;
    }
}