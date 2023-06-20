import processing.core.PApplet;

/**
 * Verwaltet das GameGrid
 */
public class GridController {

     private GameGrid gameGrid; // Instanz des Spielgitters
     static int speed = 50;


    /**
     * Erstellt einen GridController.
     *
     * @param applet Die PApplet Instanz
     * @param rows Die Anzahl der Zeilen des Spielgitters
     * @param cols Die Anzahl der Spalten des Spielgitters
     */
    public GridController(PApplet applet, int rows, int cols) {
        this.gameGrid = new GameGrid(applet, rows, cols);
    }


    /**
     * Leert volle Zeilen und bewegt andere Zeilen abwärts.
     *
     * @param gameGrid Die Instanz des Spielgitters
     * @return Die Anzahl der Punkte, basierend auf der Anzahl der gelöschten Zeilen
     */
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
            if (speed > 4) {
                speed -= 3;
            }
            else{
                speed = 3;
            }
        }

        return points;
    }


    /**
     * Überprüft, ob eine Zeile voll ist.
     *
     * @param gameGrid Die Instanz des Spielgitters
     * @param row Der Index der zu überprüfenden Zeile
     * @return True, wenn die Zeile voll ist, sonst false
     */
    private boolean isRowFull(GameGrid gameGrid, int row) {
        for (int i = 0; i < gameGrid.getCols(); i++) {
            if (gameGrid.getPosition(row, i) == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * Leert eine bestimmte Zeile.
     *
     * @param gameGrid Die Instanz des Spielgitters
     * @param row Der Index der zu leerenden Zeile
     */
    private void clearRow(GameGrid gameGrid, int row) {
        for (int i = 0; i < gameGrid.getCols(); i++) {
            gameGrid.setPosition(row, i, 0);
        }
    }


    /**
     * Bewegt eine bestimmte Zeile abwärts.
     *
     * @param gameGrid Die Instanz des Spielgitters
     * @param row Der Index der zu bewegenden Zeile
     * @param howMany Die Anzahl der Zeilen, um die die Zeile abwärts bewegt werden soll
     */
    private void moveRowDown(GameGrid gameGrid, int row, int howMany) {
        for (int i = 0; i < gameGrid.getCols(); i++) {
            gameGrid.setPosition(row + howMany, i, gameGrid.getPosition(row, i));
            gameGrid.setPosition(row, i, 0);
        }
    }
}