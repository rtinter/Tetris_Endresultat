import processing.core.PApplet;

/**
 * Die Klasse GameGrid repräsentiert das Spielraster im Tetris-Spiel.
 */
public class GameGrid {
    // Attribute
    int[][] grid;

    protected int rows;
    protected int cols;
    int cellSize = 35;

    private PApplet _applet;

    /**
     * Konstruktor - Erzeugt ein neues GameGrid-Objekt mit der angegebenen Anzahl von Zeilen und Spalten.
     *
     * @param rows Anzahl der Zeilen im Spielraster.
     * @param cols Anzahl der Spalten im Spielraster.
     */
    public GameGrid(PApplet applet, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new int[rows][cols];
        _applet = applet;
    }

    /**
     * Gibt die Anzahl der Zeilen im Spielraster zurück.
     *
     * @return Anzahl der Zeilen im Spielraster.
     */
    public int getRows() {
        return rows;
    }

    /**
     * Gibt die Anzahl der Spalten im Spielraster zurück.
     *
     * @return Anzahl der Spalten im Spielraster.
     */
    public int getCols() {
        return cols;
    }

    /**
     * Gibt den Wert an der angegebenen Position im Spielraster zurück.
     *
     * @param row Zeilenindex.
     * @param col Spaltenindex.
     * @return Wert an der Position (row, col) im Spielraster.
     */
    public int getPosition(int row, int col) {
        return grid[row][col];
    }

    /**
     * Setzt den Wert an der angegebenen Position im Spielraster.
     *
     * @param row   Zeilenindex.
     * @param col   Spaltenindex.
     * @param value Neuer Wert.
     */
    public void setPosition(int row, int col, int value) {
        grid[row][col] = value;
    }

    /**
     * Initialisiert das Spielraster, indem alle Werte auf 0 gesetzt werden.
     */
    public void setup() {
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                setPosition(row, col, 0);
            }
        }
    }

    /**
     * Überprüft, ob die angegebene Position im Spielraster leer ist.
     *
     * @param row Zeilenindex.
     * @param col Spaltenindex.
     * @return True, wenn die Position leer ist, andernfalls False.
     */
    public boolean IsEmpty(int row, int col) {
        return grid[row][col] == 0;
    }

    /**
     * Zeichnet einen Block auf dem Spielraster mit den angegebenen Positionen und Eigenschaften.
     *
     * @param blockPositions Die Positionen des Blocks.
     * @param startOffset    Der Startversatz des Blocks.
     * @param blockId        Die ID des Blocks.
     * @param currentRotation Der aktuelle Rotationszustand des Blocks.
     */

   public void drawBlock(Block block) {
       int currentRotation = block.currentRotation;
       var blockPositions = block.getTiles();
       var startOffset = block.getCurrentPosition();

       int blockId = block.getId();

        for (int i = 0, j = currentRotation; i < blockPositions[j].length; i++) {
            int row = blockPositions[j][i][0] + startOffset[0];
            int col = blockPositions[j][i][1] + startOffset[1];
            setPosition(row, col, blockId);
        }
    }

    public void drawNextBlock(Block block) {
        int currentRotation = block.currentRotation;
        var blockPositions = block.getTiles();
        var startCoords = block.startCoordsForNextBlock();

        int blockId = block.getId();

        for (int i = 0, j = currentRotation; i < blockPositions[j].length; i++) {
            int row = blockPositions[j][i][0] + startCoords[0];
            int col = blockPositions[j][i][1] + startCoords[1];
            setPosition(row, col, blockId);
        }
    }

    /**
     * Löscht einen Block aus dem Spielraster mit den angegebenen Positionen und Eigenschaften.
     *
     * @param blockPositions Die Positionen des Blocks.
     * @param startOffset    Der Startversatz des Blocks.
     * @param currentRotation Der aktuelle Rotationszustand des Blocks.
     */
    public void deleteBlock(Block block) {
        int rotation = block.currentRotation;
        int blockPositions[][][] = block.getTiles();
        var pos = block.getCurrentPosition();

        for (int i = 0, j = rotation; i < blockPositions[j].length; i++) {
            int row = blockPositions[j][i][0] + pos[0];
            int col = blockPositions[j][i][1] + pos[1];
            setPosition(row, col, 0);
        }
    }

    /**
     * Zeichnet das Spielraster auf der gegebenen PApplet-Instanz.
     *
     */
    public void draw() {
        // Färbe Gamegrid je nach Wert
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int value = grid[row][col];
                float x = col * cellSize;
                float y = row * cellSize;
                _applet.stroke(0);

                switch (value) {
                    case 0:
                        _applet.fill(255);
                        break;
                    case 1:
                        _applet.fill(0, 191, 255); // Blau
                        break;
                    case 2:
                        _applet.fill(255, 127, 36); // Orange
                        break;
                    case 3:
                        _applet.fill(155, 62, 150); // Lila
                        break;
                    case 4:
                        _applet.fill(0, 205, 205); // Hellblau
                        break;
                    case 5:
                        _applet.fill(255, 64, 0); // Rot
                        break;
                    case 6:
                        _applet.fill(255, 215, 0); // Gelb
                        break;
                    default:
                        _applet.fill(0, 255, 127); // Grün
                }
                _applet.rect(x + 40, y + 40, cellSize, cellSize); // +40px = Versatz
            }
        }
    }
}