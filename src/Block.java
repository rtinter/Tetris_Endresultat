import processing.core.PApplet;


public abstract class Block {
    protected int[][][] tiles;
    protected int[] currentPosition;



    public int currentRotation = 0;


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

    public void setTiles(int[][][] newTiles) {
        tiles = newTiles;
    }


    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public void setCoordinates(int[] newCoordinates) {
        currentPosition = newCoordinates;
    }


    // Startkoordinaten für das nextBlockArray
    // Wichtig, da x der startpositionen für das spielfeld zu groß
    protected int[] startCoordsForNextBlock() {
        int[] coords = {1, 0};
        return coords;
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


    public boolean rotate(GameGrid gameGrid) {
        int[][][] newTiles = tiles.clone();
        int nextRotation = (currentRotation + 1) % newTiles.length;

        int[] newPosition = currentPosition.clone();


        if (canBlockFit(newTiles, newPosition, gameGrid)) {
            setTiles(newTiles); // Aktualisiere die tiles-Eigenschaft
            currentRotation = nextRotation;
            return true; // Rotation erfolgreich
        }

        return false; // Rotation nicht möglich
    }


    private boolean canBlockFit(int[][][] tiles, int[] position, GameGrid gameGrid) {
        for (int i = 0; i < tiles[currentRotation].length; i++) {
            int row = tiles[currentRotation][i][0] + position[0];
            int col = tiles[currentRotation][i][1] + position[1];

            // Kollisionserkennung mit den Seiten des Spielfelds
            if (row < 0 || col < 0 || row >= gameGrid.getRows() || col >= gameGrid.getCols() || !gameGrid.IsEmpty(row, col)) {
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
