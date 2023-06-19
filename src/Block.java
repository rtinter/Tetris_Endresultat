
public abstract class Block {
    protected int[][][] tiles; // Die Darstellung des Blocks als Array von Tiles

    protected int[] currentPosition; // Die aktuelle Position des Blocks

    private int currentRotation = 0; // Die aktuelle Rotation des Blocks

    private GameGrid _gameGrid;


    public Block(GameGrid grid) {
        tiles = createTiles();
        currentPosition = startCoords();
        _gameGrid = grid;
    }


    protected abstract int[][][] createTiles();


    protected abstract int getId();

    public int getCurrentRotation() {
        return currentRotation;
    }

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


    protected int[] startCoordsForNextBlock() {
        int[] coords = {1, 0};
        return coords;
    }


    public boolean moveDown() {
        int[] currentPosition = getCurrentPosition();

        int[] newPosition = {currentPosition[0] + 1, currentPosition[1]};

        if (canBlockFit(newPosition)) {
            currentPosition[0]++;
            return true;
        } else {
            return false;
        }
    }


    public boolean moveLeft() {
        int[] currentPosition = getCurrentPosition();

        int[] newPosition = {currentPosition[0], currentPosition[1] - 1};

        if (canBlockFit(newPosition)) {
            currentPosition[1]--;
            return true;
        } else {
            return false;
        }
    }

    public boolean moveRight() {
        int[] currentPosition = getCurrentPosition();

        int[] newPosition = {currentPosition[0], currentPosition[1] + 1};

        if (canBlockFit(newPosition)) {
            currentPosition[1]++;
            return true;
        } else {
            return false;
        }
    }


    public boolean rotate() {
        int[][][] newTiles = tiles.clone();
        int nextRotation = (currentRotation + 1) % newTiles.length;

        int[] newPosition = currentPosition.clone();

        // Überprüfe, ob der gedrehte Block noch innerhalb der Grenzen des Spielfelds liegt
        if (!isRotationOutOfBounds(newTiles, newPosition, _gameGrid, nextRotation)) {
            if (canBlockFit(newPosition)) {
                setTiles(newTiles); // Aktualisiere die tiles-Eigenschaft
                currentRotation = nextRotation;
                return true; // Rotation erfolgreich
            }
        }
        return false; // Rotation nicht möglich
    }


    private boolean isRotationOutOfBounds(int[][][] tiles, int[] position, GameGrid gameGrid, int nextRotation) {
        for (int i = 0; i < tiles[nextRotation].length; i++) {
            int row = tiles[nextRotation][i][0] + position[0];
            int col = tiles[nextRotation][i][1] + position[1];

            // Überprüfen, ob der Block nach der Drehung außerhalb des Spielfelds liegt
            if (row < 0 || col < 0 || row >= gameGrid.getRows() || col >= gameGrid.getCols()) {
                return true;
            }
        }
        return false;
    }



    public boolean canBlockFit(int[] position) {
        var tiles = this.getTiles();
        for (int i = 0; i < tiles[currentRotation].length; i++) {
            int row = tiles[currentRotation][i][0] + position[0];
            int col = tiles[currentRotation][i][1] + position[1];

            // Kollisionserkennung mit den Seiten des Spielfelds
            if (row < 0 || col < 0 || row >= _gameGrid.getRows() || col >= _gameGrid.getCols() || !_gameGrid.IsEmpty(row, col)) {
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