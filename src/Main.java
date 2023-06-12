import processing.core.PApplet;

/**
 * Die Hauptklasse des Tetris-Spiels.
 */
public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main(Main.class);
    }

    static int speed = 50;


    BlockFactory blockFactory = new BlockFactory();
    Block nextBlockInQueue = blockFactory.blockQueue();


    GameGrid gridPlayground = new GameGrid(20, 10);
    GameGrid gridNextStone = new GameGrid(4, 4);

    Kollision kollision = new Kollision(gridPlayground.getRows(), gridPlayground.getCols());

    Block currentBlock;
    GameState gameState;

    int score = 0;

    int timerStart;

    int elapsedSeconds;

    int pauseTime;

    /**
     * Konfiguriert die Größe des Anzeigefensters und initialisiert das Spiel.
     */
    @Override
    public void settings() {
        size(600, 780);

        timerStart = millis();

        currentBlock = blockFactory.getNextBlock();
        nextBlockInQueue = blockFactory.blockQueue();


        // Alle Zellen mit 0 füllen
        gridPlayground.setupGrid(gridPlayground);

        // Block im gridNextStone zeichnen
        gridNextStone.drawBlock(nextBlockInQueue.getTiles(), nextBlockInQueue.startCoordsForNextBlock(), nextBlockInQueue.getId(), nextBlockInQueue.currentRotation);

        gameState = new GameState(this, gridPlayground.getRows(), gridPlayground.getCols());

        gameState.startScreen();
    }

    /**
     * Wird kontinuierlich aufgerufen, um das Spiel zu aktualisieren und zu zeichnen.
     */
    @Override
    public void draw() {

        if (gameState.getState() == GameState.State.START) {
            background(255);
            textSize(60);
            fill(0);
            textAlign(CENTER, CENTER);
            text("TETRIS", width / 2, height / 2 - 40);
            textSize(24);
            text("Press 'Enter' to begin", width / 2, height / 2 + 20);
        }

        if (gameState.getState() == GameState.State.GAME_OVER) {
            background(255);
            textSize(60);
            fill(0);
            textAlign(CENTER, CENTER);
            text("GAME OVER", width / 2, height / 2 - 40);
            textSize(24);
            text("Score: " + score, width / 2, height / 2 + 60);
            text("Time: " + elapsedSeconds + " seconds", width / 2, height / 2 + 90);
            textSize(16);
            text("Press 'R' to restart", width / 2, height / 2 + 120);
        }

        if (gameState.getState() == GameState.State.RUNNING) {

            elapsedSeconds = (millis() - timerStart) / 1000;


            background(255);

            BlockFactory blockFactory = new BlockFactory();


            gridPlayground.draw(this);


            pushMatrix(); //Speichern des aktuellen Matrix-Zustands (sonst verschiebt sich die Score/Timer Anzeige)
            translate(380, 0);
            gridNextStone.draw(this);
            popMatrix(); //Wiederherstellen des letzten Matrix-Zustands (sonst verschiebt sich die Score/Timer Anzeige)


            if (frameCount % speed == 0) {

                // Löscht den aktuellen Block bevor er sich bewegt
                gridPlayground.deleteBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.currentRotation);


                // Bewegt den Block nach unten
                boolean movedDown = currentBlock.moveDown(gridPlayground);


                // Überprüft, ob der Block erfolgreich nach unten bewegt wurde
                if (!movedDown) {
                    // Block konnte nicht nach unten bewegt werden, daher wird er eingefroren
                    currentBlock.freeze(gridPlayground);


                    int points = kollision.clearFullRows(gridPlayground); // Punkte zuweisen
                    score += points;

                    gridNextStone.setupGrid(gridNextStone);

                    // Überprüfen, ob der nächste Block platziert werden kann
                    if (!nextBlockInQueue.canBlockFit(nextBlockInQueue.getTiles(), nextBlockInQueue.startCoords(), gridPlayground)) {
                        // Spiel beenden, wenn der nächste Block nicht platziert werden kann
                        gameState.gameOver();
                        return;
                    }

                    // Erzeugt einen neuen Block
                    currentBlock = nextBlockInQueue;

                    // Den darauffolgenden im nextblockfeld anzeigen lassen
                    nextBlockInQueue = blockFactory.getNextBlock();
                    gridNextStone.drawBlock(nextBlockInQueue.getTiles(), nextBlockInQueue.startCoordsForNextBlock(), nextBlockInQueue.getId(), nextBlockInQueue.currentRotation);
                }
            }

            // Zeichnet den Block nach jedem Tick neu
            gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId(), currentBlock.currentRotation);

            // Zeigt den Punktestand
            fill(0);
            textSize(24);
            text("Score: " + score, 480, 300); //x-Koordinate verändert, da sie nach Spielstart verschoben wurde

            //Zeigt die vergangene Zeit
            fill(0);
            textSize(24);
            text("Time: " + elapsedSeconds, 480, 350); //x-Koordinate verändert, da sie nach Spielstart verschoben wurde
        }

        // Bei Pausierung: "PAUSE"
        else if (gameState.getState() == GameState.State.PAUSED) {
            textSize(60);
            fill(255, 0, 0);
            textAlign(CENTER, CENTER);
            text("PAUSE", width / 2, height / 2);
        }


    }

    /**
     * Wird aufgerufen, wenn eine Taste gedrückt wird.
     */
    public void keyPressed() {
        switch (keyCode) {
            case LEFT -> { // Bewegt den Block nach links
                gridPlayground.deleteBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.currentRotation);
                currentBlock.moveLeft(gridPlayground);
                gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId(), currentBlock.currentRotation);
            }
            case RIGHT -> { // Bewegt den Block nach rechts
                gridPlayground.deleteBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.currentRotation);
                currentBlock.moveRight(gridPlayground);
                gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId(), currentBlock.currentRotation);
            }
            case DOWN -> { // Bewegt den Block nach unten
                gridPlayground.deleteBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.currentRotation);
                currentBlock.moveDown(gridPlayground);
                gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId(), currentBlock.currentRotation);
            }
            case UP -> { //Rotiert den Block um 90 Grad
                gridPlayground.deleteBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.currentRotation);
                currentBlock.rotate(gridPlayground);
                gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId(), currentBlock.currentRotation);
            }
            default -> {
            }
        }

        // Tasteneingabe um das Spiel zu starten
        if (key == ENTER) {
            if (gameState.getState() == GameState.State.START) {
                gameState.startGame();
            }
        }

        //Tasteneingabe zum Pausieren des Spiels
        if (key == ' ') {
            if (gameState.getState() == GameState.State.RUNNING) {
                gameState.pauseGame();
            } else if (gameState.getState() == GameState.State.PAUSED) {
                gameState.resumeGame();
            }
        }

        //Tasteneingabe zum Neustarten
        if (key == 'R' || key == 'r') {
            if (gameState.getState() == GameState.State.GAME_OVER) {
                resetGame();
                gameState.startGame();
            }
        }
    }

    /**
     * Setzt das Spiel zurück.
     */
    public void resetGame() {
        gameState = new GameState(this, gridPlayground.getRows(), gridPlayground.getCols());

        // Zurücksetzen des Scores und der Geschwindigkeit
        score = 0;
        speed = 50;

        // Zurücksetzen des Timers
        timerStart = millis();
        elapsedSeconds = 0;

        // Zurücksetzen des Grids und der Blöcke
        gridPlayground.setupGrid(gridPlayground);
        gridNextStone.setupGrid(gridNextStone);

        // Neuen Block auf gewünschte Position setzen
        currentBlock.setCoordinates(currentBlock.startCoords());

        // Zeichnen des neuen Blocks
        gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId(), currentBlock.currentRotation);
        gridNextStone.drawBlock(nextBlockInQueue.getTiles(), nextBlockInQueue.startCoordsForNextBlock(), nextBlockInQueue.getId(), nextBlockInQueue.currentRotation);
    }

    /**
     * Setzt den Timerstart zurück.
     */
    public void resetTimerStart() {
        this.timerStart = millis();
    }
}