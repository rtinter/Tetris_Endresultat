
public class O_Block extends Block {

    public O_Block(GameGrid grid){ super(grid); }

    protected int[][][] createTiles() {
        int[][][] tiles = new int[4][4][2]; // 1. Dimension = Rotationstate, 2. Dimension = Position der Tiles im Objekt, 3. Dimension = x- und y-Koordinaten
        tiles[0] = new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
        return tiles;
    }

    @Override
    public boolean rotate() {
        // O-Blöcke dürfen nicht rotieren, daher wird immer "false" zurückgegeben
        return false;
    }

    public int getId() {
        return 6;
    }

    public int[] startCoords() {
        return new int[] { 0, 4 };
    }

    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }

    public int[][][] getTiles() {
        return tiles;
    }
}