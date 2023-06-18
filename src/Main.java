import processing.core.PApplet;

public class Main extends PApplet {

    static int speed = 50;

    GameGrid gridPlayground;
    GameGrid gridNextStone;

    Kollision kollision;

    GameState gameState;

    int score = 0;

    int timerStart;

    int elapsedSeconds;

    int pauseTime;



    public static void main(String[] args) {
        PApplet.main(Main.class);
    }

    @Override
    public void settings() {
        size(600, 780);

        gridPlayground = new GameGrid(this, 20, 10);
        gridNextStone = new GameGrid(this, 4, 4);
        kollision = new Kollision(this, gridPlayground.getRows(), gridPlayground.getCols());

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
                gameState.drawStartScene("TETRIS", "Press 'Enter' to begin", 20);
            }
            case RUNNING -> {
                gameState.drawGameScene(factory, currentBlock, nextBlockInQueue);
            }
            case PAUSED -> {
                gameState.drawPauseScene();
            }
            case GAME_OVER -> {
                gameState.drawGameOverScene();
            }
        }
    }


    public void keyPressed() {
        var currentBlock = BlockFactory.getInstance().getCurrentBlock();
        switch (keyCode) {
            case LEFT -> { // Bewegt den Block nach links
                gridPlayground.deleteBlock(currentBlock);
                currentBlock.moveLeft();
                gridPlayground.drawBlock(currentBlock);
            }
            case RIGHT -> { // Bewegt den Block nach rechts
                gridPlayground.deleteBlock(currentBlock);
                currentBlock.moveRight();
                gridPlayground.drawBlock(currentBlock);
            }
            case DOWN -> { // Bewegt den Block nach unten
                gridPlayground.deleteBlock(currentBlock);
                currentBlock.moveDown();
                gridPlayground.drawBlock(currentBlock);
            }
            case UP -> { //Rotiert den Block um 90 Grad
                gridPlayground.deleteBlock(currentBlock);
                currentBlock.rotate();
                gridPlayground.drawBlock(currentBlock);
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