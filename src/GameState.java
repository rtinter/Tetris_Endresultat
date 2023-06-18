public class GameState {

    private Kollision kollision;


    public enum State {
        START,      // Das Spiel befindet sich im Startzustand
        RUNNING,    // Das Spiel läuft
        PAUSED,     // Das Spiel ist pausiert
        GAME_OVER   // Das Spiel ist vorbei
    }

    private State state;    // Der aktuelle Zustand des Spiels
    private final Main main;    // Die Main-Instanz, die das Spiel steuert
    private final int score;    // Der aktuelle Punktestand des Spiels
    private final int elapsedTime;   // Die vergangene Spielzeit in Millisekunden

    public GameState(Main main, int rows, int cols) {
        this.kollision = new Kollision(main, rows + 1, cols);
        this.main = main;
        this.state = State.RUNNING;
        this.score = 0;
        this.elapsedTime = 0;
    }


    public void startScreen() {
        this.state = State.START;
    }


    public void startGame() {
        this.state = State.RUNNING;
        main.setup();
        main.resetTimerStart();
    }

    public void pauseGame() {
        if (this.state == State.RUNNING) {
            this.state = State.PAUSED;
            main.pauseTime = main.millis();
        }
    }

    public void resumeGame() {
        if (this.state == State.PAUSED) {
            this.state = State.RUNNING;
            main.timerStart += main.millis() - main.pauseTime;
        }
    }

    public void gameOver() {
        this.state = State.GAME_OVER;
    }

    public State getState() {
        return this.state;
    }

    protected void drawPauseScene() {
        main.textSize(60);
        main.fill(255, 0, 0);
        main.textAlign(main.CENTER, main.CENTER);
        main.text("PAUSE", main.width / 2, main.height / 2);
    }

    protected void drawGameScene(BlockFactory factory, Block currentBlock, Block nextBlockInQueue) {
        main.elapsedSeconds = (main.millis() - main.timerStart) / 1000;

        main.background(255);

        BlockFactory blockFactory = BlockFactory.getInstance();

        main.gridPlayground.draw();

        main.pushMatrix(); //Speichern des aktuellen Matrix-Zustands (sonst verschiebt sich die Score/Timer Anzeige)
        main.translate(380, 0);
        main.gridNextStone.draw();
        main.popMatrix(); //Wiederherstellen des letzten Matrix-Zustands (sonst verschiebt sich die Score/Timer Anzeige)

        if (main.frameCount % main.speed == 0) {

            // Löscht den aktuellen Block bevor er sich bewegt
            main.gridPlayground.deleteBlock(currentBlock);


            // Bewegt den Block nach unten
            boolean movedDown = currentBlock.moveDown();


            // Überprüft, ob der Block erfolgreich nach unten bewegt wurde
            if (!movedDown) {
                // Block konnte nicht nach unten bewegt werden, daher wird er eingefroren
                currentBlock.freeze(main.gridPlayground);
                factory.setCurrentBlock(nextBlockInQueue);


                int points = kollision.clearFullRows(main.gridPlayground); // Punkte zuweisen
                main.score += points;

                main.gridNextStone.setup();

                // Überprüfen, ob der nächste Block platziert werden kann
                if (!nextBlockInQueue.moveDown()) {
                    // Spiel beenden, wenn der nächste Block nicht platziert werden kann
                    main.gameState.gameOver();
                    return;
                }

                // Den darauffolgenden im nextblockfeld anzeigen lassen
                var next = blockFactory.initNextBlock();
                factory.setCurrentBlock(nextBlockInQueue);
                factory.setNextBlock(next);
                main.gridNextStone.drawNextBlock(next);
            }
        }

        // Zeichnet den Block nach jedem Tick neu
        main.gridPlayground.drawBlock(currentBlock);

        // Zeigt den Punktestand
        main.fill(0);
        main.textSize(24);
        main.text("Score: " + score, 480, 300); //x-Koordinate verändert, da sie nach Spielstart verschoben wurde

        //Zeigt die vergangene Zeit
        main.fill(0);
        main.textSize(24);
        main.text("Time: " + main.elapsedSeconds, 480, 350); //x-Koordinate verändert, da sie nach Spielstart verschoben wurde
    }

    protected void drawGameOverScene() {
        drawStartScene("GAME OVER", "Score: " + score, 60);
        main.text("Time: " + main.elapsedSeconds + " seconds", main.width / 2, main.height / 2 + 90);
        main.textSize(16);
        main.text("Press 'R' to restart", main.width / 2, main.height / 2 + 120);
    }

    protected void drawStartScene(String TETRIS, String str, int x) {
        main.background(255);
        main.textSize(60);
        main.fill(0);
        main.textAlign(main.CENTER, main.CENTER);
        main.text(TETRIS, main.width / 2, main.height / 2 - 40);
        main.textSize(24);
        main.text(str, main.width / 2, main.height / 2 + x);
    }
}
