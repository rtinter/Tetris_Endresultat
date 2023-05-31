public class J_Block extends Block{

    private final int[][][] tiles = createTiles();


    protected int[][][] createTiles() {
        int[][][] tiles = new int[4][4][2]; // 1. Dimension = Rotationstate, 2. Dimension = Position der Tiles im Objekt, 3. Dimension = x- und y-Koordinaten
        tiles[0] = new int[][] { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 1, 2 } };
        tiles[1] = new int[][] { { 0, 1 }, { 0, 2 }, { 1, 1 }, { 2, 1 } };
        tiles[2] = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 2, 2 } };
        tiles[3] = new int[][] { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 2, 2 } };
        return tiles;
    }


    public int getId() {
        return 5;
    }

    protected int[] getStartOffset() {
        return new int[] { 4, 3 };
    }

    public int[][][] getTiles() {
        return tiles;
    }


}
