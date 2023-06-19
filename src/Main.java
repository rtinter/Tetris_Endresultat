import processing.core.PApplet;

public class Main extends PApplet {

    static int speed = 50;

    GameGrid gridPlayground;
    GameGrid gridNextStone;

    GridController gridController;

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
        gridController = new GridController(this, gridPlayground.getRows(), gridPlayground.getCols());
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