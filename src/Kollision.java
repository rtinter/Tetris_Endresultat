/**
 * Die Klasse Kollision erweitert die GameGrid-Klasse und enthält Methoden zur Kollisionserkennung
 * und zum Löschen vollständiger Reihen im Spielraster.
 */
public class Kollision {

     private GameGrid gameGrid;

    /**
     * Konstruktor für die Kollisionsklasse.
     *
     * @param rows Die Anzahl der Zeilen im Spielraster.
     * @param cols Die Anzahl der Spalten im Spielraster.
     */
    public Kollision(int rows, int cols) {
        this.gameGrid = new GameGrid(rows, cols);
    }

    /**
     * Löscht vollständige Reihen im Spielraster und gibt die Punkteanzahl zurück.
     *
     * @param gameGrid Das Spielraster.
     * @return Die Punkteanzahl für die gelöschten Reihen.
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
            if (Main.speed > 4) {
                Main.speed -= 3;
            }
            else{
                Main.speed = 3;
            }
        }

        return points;
    }

    /**
     * Überprüft, ob eine bestimmte Reihe im Spielraster vollständig gefüllt ist.
     *
     * @param gameGrid Das Spielraster.
     * @param row      Die zu überprüfende Reihe.
     * @return True, wenn die Reihe vollständig gefüllt ist, ansonsten False.
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
     * Löscht eine bestimmte Reihe im Spielraster.
     *
     * @param gameGrid Das Spielraster.
     * @param row      Die zu löschende Reihe.
     */
    private void clearRow(GameGrid gameGrid, int row) {
        for (int i = 0; i < gameGrid.getCols(); i++) {
            gameGrid.setPosition(row, i, 0);
        }
    }

    /**
     * Verschiebt alle Reihen über einer bestimmten Reihe im Spielraster nach unten.
     *
     * @param gameGrid Das Spielraster.
     * @param row      Die Startreihe, ab der die Verschiebung erfolgen soll.
     * @param howMany  Die Anzahl der zu verschiebenden Reihen.
     */
    private void moveRowDown(GameGrid gameGrid, int row, int howMany) {
        for (int i = 0; i < gameGrid.getCols(); i++) {
            gameGrid.setPosition(row + howMany, i, gameGrid.getPosition(row, i));
            gameGrid.setPosition(row, i, 0);
        }
    }
}