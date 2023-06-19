import processing.core.PApplet;

/**
 * GameGrid Klasse repräsentiert das Spielfeld des Spiels.
 */
public class GameGrid {

    protected int[][] grid;  // 2D-Gitter, das das Spielfeld darstellt

    protected int rows;  // Anzahl der Reihen im Gitter

    protected int cols;  // Anzahl der Spalten im Gitter

    int cellSize = 35;  // Größe jeder Zelle

    private PApplet _applet;  // Processing Applet, das für die grafische Darstellung verwendet wird


    /**
     * Konstruktor der GameGrid Klasse.
     * @param applet Das Processing Applet, das für die grafische Darstellung verwendet wird.
     * @param rows Anzahl der Reihen im Gitter.
     * @param cols Anzahl der Spalten im Gitter.
     */
    public GameGrid(PApplet applet, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new int[rows][cols];
        _applet = applet;
    }


    /**
     * Gibt die Anzahl der Reihen im Gitter zurück.
     * @return Anzahl der Reihen.
     */
    public int getRows() {
        return rows;
    }


    /**
     * Gibt die Anzahl der Spalten im Gitter zurück.
     * @return Anzahl der Spalten.
     */
    public int getCols() {
        return cols;
    }


    /**
     * Gibt den Wert an der angegebenen Position im Gitter zurück.
     * @param row Reihe der Position.
     * @param col Spalte der Position.
     * @return Der Wert an der angegebenen Position.
     */
    public int getPosition(int row, int col) {
        return grid[row][col];
    }


    /**
     * Setzt den Wert an der angegebenen Position im Gitter.
     * @param row Reihe der Position.
     * @param col Spalte der Position.
     * @param value Der zu setzende Wert.
     */
    public void setPosition(int row, int col, int value) {
        grid[row][col] = value;
    }


    /**
     * Initialisiert das Gitter, indem es alle Positionen auf 0 setzt.
     */
    public void setup() {
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getCols(); col++) {
                setPosition(row, col, 0);
            }
        }
    }


    /**
     * Überprüft, ob die angegebene Position im Gitter leer ist (Wert 0 hat).
     * @param row Reihe der Position.
     * @param col Spalte der Position.
     * @return True, wenn die Position leer ist, sonst False.
     */
    public boolean IsEmpty(int row, int col) {
        return grid[row][col] == 0;
    }


    /**
     * Zeichnet den gegebenen Block auf das Gitter.
     * @param block Der zu zeichnende Block.
     */
    public void drawBlock(Block block) {
        int currentRotation = block.getCurrentRotation();
        var blockPositions = block.getTiles();
        var startOffset = block.getCurrentPosition();

        int blockId = block.getId();

        for (int i = 0, j = currentRotation; i < blockPositions[j].length; i++) {
            int row = blockPositions[j][i][0] + startOffset[0];
            int col = blockPositions[j][i][1] + startOffset[1];
            setPosition(row, col, blockId);
        }
    }


    /**
     * Zeichnet den nächsten Block auf das Gitter.
     * @param block Der nächste zu zeichnende Block.
     */
    public void drawNextBlock(Block block) {
        int currentRotation = block.getCurrentRotation();
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
     * Löscht den gegebenen Block vom Gitter.
     * @param block Der zu löschende Block.
     */
    public void deleteBlock(Block block) {
        int rotation = block.getCurrentRotation();
        int blockPositions[][][] = block.getTiles();
        var pos = block.getCurrentPosition();

        for (int i = 0, j = rotation; i < blockPositions[j].length; i++) {
            int row = blockPositions[j][i][0] + pos[0];
            int col = blockPositions[j][i][1] + pos[1];
            setPosition(row, col, 0);
        }
    }


    /**
     * Zeichnet das Gitter und färbt jede Zelle je nach ihrem Wert ein.
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