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


    public boolean moveDown(GameGrid gameGrid) {
        int[][][] tiles = getTiles();
        int[] currentPosition = getCurrentPosition();

        int[] newPosition = {currentPosition[0] + 1, currentPosition[1]};

        if (canBlockFit(tiles, newPosition, gameGrid)) {
            currentPosition[0]++;
            return true;
        } else {


            return false;
        }
    }

    public boolean moveLeft(GameGrid gameGrid) {
        int[][][] tiles = getTiles();
        int[] currentPosition = getCurrentPosition();

        int[] newPosition = {currentPosition[0], currentPosition[1] - 1};

        if (canBlockFit(tiles, newPosition, gameGrid)) {
            currentPosition[1]--;
            return true;
        } else {
            return false;
        }
    }

    public boolean moveRight(GameGrid gameGrid) {
        int[][][] tiles = getTiles();
        int[] currentPosition = getCurrentPosition();

        int[] newPosition = {currentPosition[0], currentPosition[1] + 1};

        if (canBlockFit(tiles, newPosition, gameGrid)) {
            currentPosition[1]++;
            return true;
        } else {
            return false;
        }
    }




    private boolean canBlockFit(int[][][] tiles, int[] position, GameGrid gameGrid) {
        for (int i = 0; i < tiles[currentRotation].length; i++) {
            int row = tiles[currentRotation][i][0] + position[0];
            int col = tiles[currentRotation][i][1] + position[1];

            if (row >= gameGrid.getRows() || col >= gameGrid.getCols() || !gameGrid.IsEmpty(row, col)) {
                return false;
            }
        }
        return true;
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
