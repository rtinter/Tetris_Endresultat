import processing.core.PApplet;

public class Main extends PApplet {

    static int speed = 50;

    GameGrid gridPlayground;
    GameGrid gridNextStone;

    Kollision kollision;

    GameState gameState;

    KeyHandler keyHandler;

    int score = 0;

    int timerStart;

    int elapsedSeconds;

    int pauseTime;



    public static void main(String[] args) {
        PApplet.main(Main.class);
    }

    @Override
    public void keyPressed() {
        keyHandler.handleKeyPressed();
    }

    @Override
    public void settings() {
        size(600, 780);

        gridPlayground = new GameGrid(this, 20, 10);
        gridNextStone = new GameGrid(this, 4, 4);
        kollision = new Kollision(this, gridPlayground.getRows(), gridPlayground.getCols());
        keyHandler = new KeyHandler(this);

        timerStart = millis();

        var blockFactory = BlockFactory.initBlockFactory(gridPlayground);

        var currentBlock = blockFactory.getBlock();
        blockFactory.setCurrentBlock(currentBlock);
        var nextBlockInQueue = blockFactory.getBlock();
        blockFactory.setNextBlock(nextBlockInQueue);


        // Alle Zellen mit 0 füllen
        gridPlayground.setup();

        // Block im gridNextStone zeichnen
        gridNextStone.drawNextBlock(nextBlockInQueue);

        gameState = new GameState(this, gridPlayground.getRows(), gridPlayground.getCols());

        gameState.startScreen();
    }

    @Override
    public void draw() {
        var factory = BlockFactory.getInstance();

        var currentBlock = factory.getCurrentBlock();
        var nextBlockInQueue = factory.blockQueue();

        switch(gameState.getState()){
            case START -> {
                drawStartScene("TETRIS", "Press 'Enter' to begin", 20);
            }
            case RUNNING -> {
                drawGameScene(factory, currentBlock, nextBlockInQueue);
            }
            case PAUSED -> {
                drawPauseScene();
            }
            case GAME_OVER -> {
                drawGameOverScene();
            }
        }
    }

    private void drawPauseScene() {
        textSize(60);
        fill(255, 0, 0);
        textAlign(CENTER, CENTER);
        text("PAUSE", width / 2, height / 2);
    }

    private void drawGameScene(BlockFactory factory, Block currentBlock, Block nextBlockInQueue) {
        elapsedSeconds = (millis() - timerStart) / 1000;

        background(255);

        BlockFactory blockFactory = BlockFactory.getInstance();

        gridPlayground.draw();

        pushMatrix(); //Speichern des aktuellen Matrix-Zustands (sonst verschiebt sich die Score/Timer Anzeige)
        translate(380, 0);
        gridNextStone.draw();
        popMatrix(); //Wiederherstellen des letzten Matrix-Zustands (sonst verschiebt sich die Score/Timer Anzeige)

        if (frameCount % speed == 0) {

            // Löscht den aktuellen Block bevor er sich bewegt
            gridPlayground.deleteBlock(currentBlock);


            // Bewegt den Block nach unten
            boolean movedDown = currentBlock.moveDown();


            // Überprüft, ob der Block erfolgreich nach unten bewegt wurde
            if (!movedDown) {
                // Block konnte nicht nach unten bewegt werden, daher wird er eingefroren
                currentBlock.freeze(gridPlayground);
                factory.setCurrentBlock(nextBlockInQueue);


                int points = kollision.clearFullRows(gridPlayground); // Punkte zuweisen
                score += points;

                gridNextStone.setup();

                // Überprüfen, ob der nächste Block platziert werden kann
                if (!nextBlockInQueue.moveDown()) {
                    // Spiel beenden, wenn der nächste Block nicht platziert werden kann
                    gameState.gameOver();
                    return;
                }

                // Den darauffolgenden im nextblockfeld anzeigen lassen
                var next = blockFactory.initNextBlock();
                factory.setCurrentBlock(nextBlockInQueue);
                factory.setNextBlock(next);
                gridNextStone.drawNextBlock(next);
            }
        }

        // Zeichnet den Block nach jedem Tick neu
        gridPlayground.drawBlock(currentBlock);

        // Zeigt den Punktestand
        fill(0);
        textSize(24);
        text("Score: " + score, 480, 300); //x-Koordinate verändert, da sie nach Spielstart verschoben wurde

        //Zeigt die vergangene Zeit
        fill(0);
        textSize(24);
        text("Time: " + elapsedSeconds, 480, 350); //x-Koordinate verändert, da sie nach Spielstart verschoben wurde
    }

    private void drawGameOverScene() {
        drawStartScene("GAME OVER", "Score: " + score, 60);
        text("Time: " + elapsedSeconds + " seconds", width / 2, height / 2 + 90);
        textSize(16);
        text("Press 'R' to restart", width / 2, height / 2 + 120);
    }

    private void drawStartScene(String TETRIS, String str, int x) {
        background(255);
        textSize(60);
        fill(0);
        textAlign(CENTER, CENTER);
        text(TETRIS, width / 2, height / 2 - 40);
        textSize(24);
        text(str, width / 2, height / 2 + x);
    }


    public void resetGame() {
        gameState = new GameState(this, gridPlayground.getRows(), gridPlayground.getCols());

        // Zurücksetzen des Scores und der Geschwindigkeit
        score = 0;
        speed = 50;

        // Zurücksetzen des Timers
        timerStart = millis();
        elapsedSeconds = 0;

        // Zurücksetzen des Grids und der Blöcke
        gridNextStone.setup();
        gridPlayground.setup();


        // Neuen Block auf gewünschte Position setzen
        var factory = BlockFactory.getInstance();
        var currentBlock = factory.getCurrentBlock();
        currentBlock.setCoordinates(currentBlock.startCoords());
        var nextBlockInQueue = factory.initNextBlock();
        nextBlockInQueue.setCoordinates(nextBlockInQueue.startCoordsForNextBlock());
        factory.setNextBlock(nextBlockInQueue);

        // Zeichnen des neuen Blocks
        gridPlayground.drawBlock(currentBlock);
        gridNextStone.drawBlock(nextBlockInQueue);
    }

    public void resetTimerStart() {
        this.timerStart = millis();
    }
}