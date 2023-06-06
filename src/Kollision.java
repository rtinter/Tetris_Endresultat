public class Kollision extends GameGrid{

    public int clearFullRows(GameGrid gameGrid) {
        int clearedRows = 0;
        int points = 0;

        for (int i = gameGrid.getRows() - 1; i >= 0; i--) {
            if (isRowFull(gameGrid, i)) {
                clearRow(gameGrid, i);
                clearedRows++;
            } else if (clearedRows > 0) {
                moveRowDown(gameGrid, i, clearedRows);
            }
        }

        switch (clearedRows) {
            case 1:
                points = 40;
                break;
            case 2:
                points = 100;
                break;
            case 3:
                points = 300;
                break;
            case 4:
                points = 1200;
                break;
            default:
                break;
        }

        return points;
    }

    private boolean isRowFull(GameGrid gameGrid, int row) {
        for (int i = 0; i < gameGrid.getCols(); i++) {
            if (gameGrid.getPosition(row, i) == 0) {
                return false;
            }
        }
        return true;
    }

    private void clearRow(GameGrid gameGrid, int row) {
        for (int i = 0; i < gameGrid.getCols(); i++) {
            gameGrid.setPosition(row, i, 0);
        }
    }

    private void moveRowDown(GameGrid gameGrid, int row, int howMany) {
        for (int i = 0; i < gameGrid.getCols(); i++) {
            gameGrid.setPosition(row + howMany, i, gameGrid.getPosition(row, i));
            gameGrid.setPosition(row, i, 0);
        }
    }
    public Kollision(int rows, int cols) {
        super(rows, cols);
    }
}
