import processing.core.PApplet;
public class GameGrid{


    //Attribute
    int[][] grid;
    protected int rows;
    protected int cols;
    int cellSize = 35;

    //Konstruktor
    public GameGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new int[rows][cols];
    }

    //Getter
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }


    //________________________________________________________________________

    //Gibt den Wert des jeweiligen Punkts auf dem Array zurück
    public int getPosition(int row, int col) {
        return grid[row][col];
    }

    public void setPosition(int row, int col, int value) {
        grid[row][col] = value;
    }

    //________________________________________________________________________


    public void setupGrid(GameGrid gameGrid){
        for (int row = 0; row < gameGrid.getRows(); row++) {
            for (int col = 0; col < gameGrid.getCols(); col++) {
                gameGrid.setPosition(row, col, 0);
            }
        }
    }


    public boolean IsEmpty(int row, int col) {
        return grid[row][col] == 0;
    }

    public void drawBlock(int[][][] blockPositions, int[] startOffset, int blockId, int currentRotation) {
        for (int i = 0, j= currentRotation; i < blockPositions[j].length; i++) {
            int row = blockPositions[j][i][0] + startOffset[0];
            int col = blockPositions[j][i][1] + startOffset[1];
            setPosition(row, col, blockId);
        }
    }
    public void deleteBlock(int[][][] blockPositions, int[] startOffset,  int currentRotation) {
        for (int i = 0, j=currentRotation; i < blockPositions[j].length; i++) {
            int row = blockPositions[j][i][0] + startOffset[0];
            int col = blockPositions[j][i][1] + startOffset[1];
            setPosition(row, col, 0);
        }
    }



    //DisplayBlockInColor
    public void draw(PApplet a) {
        
        //Färbe Block je nach value
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int value = grid[row][col];
                float x = col * cellSize;
                float y = row * cellSize;
                a.stroke(0);

                switch (value) {
                    case 0:
                        a.fill(255);
                        break;
                    case 1:
                        a.fill(0, 191, 255); //Blau
                        break;
                    case 2:
                        a.fill(255, 127, 36); //Orange
                        break;
                    case 3:
                        a.fill(155, 62, 150); //Lila
                        break;
                    case 4:
                        a.fill(0, 205, 205); //Hellblau
                        break;
                    case 5:
                        a.fill(255, 64, 0); //Rot
                        break;
                    case 6:
                        a.fill(255, 215, 0); //Gelb
                        break;

                    default:
                        a.fill(0, 255, 127); // Grün
                }
                a.rect(x + 40, y + 40, cellSize, cellSize); //+40px = Versatz
            }


        }
    }
}