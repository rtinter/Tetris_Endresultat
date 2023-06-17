import processing.core.PApplet;

public class Kollision {

     private GameGrid gameGrid;

    public Kollision(PApplet applet, int rows, int cols) {
        this.gameGrid = new GameGrid(applet, rows, cols);
    }

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
            case 1 -> points = 40;
            case 2 -> points = 100;
            case 3 -> points = 300;
            case 4 -> points = 1200;
            default -> {
            }
        }

        if (clearedRows > 0) {
            if (Main.speed > 4) {
                Main.speed -= 3;
            }
            else{
                Main.speed = 3;
            }
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
}