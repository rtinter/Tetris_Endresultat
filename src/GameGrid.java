import processing.core.PApplet;
public class GameGrid{


    //Attribute
    int[][] grid;
    private int rows;
    private int cols;
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
        if(grid[row][col] == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRowFull(int row){
        for(int i = 0; i < cols;i++){
            if(grid[row][i] == 0){
                return false;
            }
        }
        return true;
    }

    public boolean isRowEmpty(int row){
        for(int i = 0; i < cols; i++){
            if(grid[row][i] != 0){
                return false;
            }
        }
        return true;
    }

    private void clearRow(int row){
        for(int i = 0; i < cols; i++){
            grid[row][i] = 0;
        }
    }

    private void moveRowDown(int row, int howMany){
        for(int i = 0; i < cols; i++){
            grid[row + howMany][i] = grid[row][i];
            grid[row][i] = 0;
        }
    }

    // Durchläuft das Array und checkt ob Reihe voll ist, sonst ob generell eine Reihe voll war
    // gibt an wieviele Reihen voll sind und füllt sie mit moveRowDown wieder auf
    public int clearFullRows(){
        int cleared = 0;
        for(int i = rows-1; i >=0; i--){
            if(isRowFull(i)){
                clearRow(i);
                cleared++;
            }
            else if(cleared > 0){
                moveRowDown(i,cleared);
            }
        }
        return cleared;
    }


    //DisplayBlocks
    public void draw(PApplet a) {
        
        //Färbe Block je nach value
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int value = grid[row][col];
                float x = col * cellSize;
                float y = row * cellSize;
                a.stroke(0);
                if (value == 0) {
                    a.fill(255);
                } else {
                    a.fill(237,12,12);
                }
                a.rect(x+40, y+40, cellSize, cellSize);
            }
        }
    }
}