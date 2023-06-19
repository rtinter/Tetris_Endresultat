public class GameState {

    private GridController gridController;


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
        this.gridController = new GridController(main, rows + 1, cols);
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

}
