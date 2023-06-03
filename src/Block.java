import processing.core.PApplet;


public abstract class Block {
    protected int[][][] tiles;
    protected int[] currentPosition;

    protected int currentRotation = 0;

    public Block() {
        tiles = createTiles();
        currentPosition = startCoords();

    }

    protected abstract int[][][] createTiles();

    public abstract int getId();

    protected abstract int[] startCoords();

    public int[][][] getTiles() {
        return tiles;
    }

    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }


    public void moveDown(GameGrid gameGrid) {
        int[][][] tiles = getTiles();
        int[] currentPosition = getCurrentPosition();

        boolean canMove = true;
        for (int i = 0; i < tiles[0].length; i++) {
            int row = tiles[0][i][0] + currentPosition[0] + 1;
            int col = tiles[0][i][1] + currentPosition[1];
            if (row >= gameGrid.getRows() || col >= gameGrid.getCols() ) {
                canMove = false;
                break;
            }

        }
        if (canMove) {
            currentPosition[0]++;
        }
    }

    public void moveLeft() {
        currentPosition[1]--; // Verringere die x-Koordinate der aktuellen Position, um den Block nach links zu bewegen
    }

    public void moveRight() {
        currentPosition[1]++; // Erhöhe die x-Koordinate der aktuellen Position, um den Block nach rechts zu bewegen
    }

    public void freeze(GameGrid gameGrid) {
        int[][][] tiles = getTiles();
        int[] position = getCurrentPosition();

        for (int i = 0; i < tiles[currentRotation].length; i++) {
            int row = tiles[currentRotation][i][0] + position[0];
            int col = tiles[currentRotation][i][1] + position[1];
            gameGrid.setPosition(row, col, getId());
        }
    }

}
