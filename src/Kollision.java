public class Kollision {

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

    public void drawBlock(GameGrid gameGrid, int[][][] blockPositions, int[] startOffset, int blockId, int currentRotation) {
        for (int i = 0, j = currentRotation; i < blockPositions[j].length; i++) {
            int row = blockPositions[j][i][0] + startOffset[0];
            int col = blockPositions[j][i][1] + startOffset[1];
            gameGrid.setPosition(row, col, blockId);
        }
    }

    public void deleteBlock(GameGrid gameGrid, int[][][] blockPositions, int[] startOffset, int currentRotation) {
        for (int i = 0, j = currentRotation; i < blockPositions[j].length; i++) {
            int row = blockPositions[j][i][0] + startOffset[0];
            int col = blockPositions[j][i][1] + startOffset[1];
            gameGrid.setPosition(row, col, 0);
        }
    }



}
