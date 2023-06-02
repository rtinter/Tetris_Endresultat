import processing.event.KeyEvent;

public class InputHandler {
  /*  private Block currentBlock;
    private GameGrid gameGrid;

    public InputHandler(Block currentBlock, GameGrid gameGrid) {
        this.currentBlock = currentBlock;
        this.gameGrid = gameGrid;
    }

    public void handleKeyPressed(KeyEvent event) {
        if (event.getKeyCode() == LEFT) {
            moveBlockLeft();
        } else if (event.getKeyCode() == RIGHT) {
            moveBlockRight();
        }
    }

    private void moveBlockLeft() {
        int[] currentOffset = currentBlock.getOffset();
        int[] newOffset = { currentOffset[0], currentOffset[1] - 1 };
        if (isOffsetValid(newOffset)) {
            currentBlock.setOffset(newOffset);
        }
    }

    private void moveBlockRight() {
        int[] currentOffset = currentBlock.getOffset();
        int[] newOffset = { currentOffset[0], currentOffset[1] + 1 };
        if (isOffsetValid(newOffset)) {
            currentBlock.setOffset(newOffset);
        }
    }

    private boolean isOffsetValid(int[] offset) {
        int[][][] blockTiles = currentBlock.getTiles();
        for (int i = 0; i < blockTiles[0].length; i++) {
            int row = blockTiles[0][i][0] + offset[0];
            int col = blockTiles[0][i][1] + offset[1];
            if (row < 0 || row >= gameGrid.getRows() || col < 0 || col >= gameGrid.getCols() || !gameGrid.isEmpty(row, col)) {
                return false;
            }
        }
        return true;
    }

    */

}
