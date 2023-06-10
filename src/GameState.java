public class GameState extends Kollision {

    public enum State {
        START,
        RUNNING,
        PAUSED,
        GAME_OVER
    }

    private State state;
    private final Main main;
    private final int score;
    private final int elapsedTime;


    public GameState(Main main, int rows, int cols) {
        super(rows + 1, cols);
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