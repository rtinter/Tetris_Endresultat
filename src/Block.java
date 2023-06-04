public abstract class Block {
    protected int[][][] tiles;
    protected int[] currentPosition;

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
            if (row >= gameGrid.getRows() || gameGrid.getPosition(row, col) != 0) {
                canMove = false;
                break;
            }
        }

        if (canMove) {
            currentPosition[0]++;
        }
    }





    public void moveLeft(GameGrid gameGrid) {
        int[][][] tiles = getTiles();
        int[] currentPosition = getCurrentPosition();

        boolean canMove = true;
        for (int i = 0; i < tiles[0].length; i++) {
            int row = tiles[0][i][0] + currentPosition[0];
            int col = tiles[0][i][1] + currentPosition[1] - 1;
            if (row >= gameGrid.getRows() || col < 0 || gameGrid.getPosition(row, col) != 0) {
                canMove = false;
                break;
            }
        }

        if (canMove) {
            currentPosition[1]--;
        }
    }

    public void moveRight(GameGrid gameGrid) {
        int[][][] tiles = getTiles();
        int[] currentPosition = getCurrentPosition();

        boolean canMove = true;
        for (int i = 0; i < tiles[0].length; i++) {
            int row = tiles[0][i][0] + currentPosition[0];
            int col = tiles[0][i][1] + currentPosition[1] + 1;
            if (row >= gameGrid.getRows() || col >= gameGrid.getCols() || gameGrid.getPosition(row, col) != 0) {
                canMove = false;
                break;
            }
        }

        if (canMove) {
            currentPosition[1]++;
        }
    }

}
