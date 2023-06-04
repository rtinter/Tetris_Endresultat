public class GameState {

    public enum State {
        START,
        RUNNING,
        PAUSED,
        GAME_OVER
    }

    private State state;
    private Main main;
    private int score;
    private int elapsedTime;

    public GameState(Main main) {
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
        if(this.state == State.RUNNING) {
            this.state = State.PAUSED;
        }
    }

    public void resumeGame() {
        if(this.state == State.PAUSED) {
            this.state = State.RUNNING;
        }
    }

    public boolean checkGameOver() {
        // Check if a freshly spawned block collides right away
        // You would need to implement the collision check logic here
        return false;
    }

    public void update() {
        if(this.state == State.RUNNING) {
            if (checkGameOver()) {
                this.state = State.GAME_OVER;
            }
            else {
                main.draw();
                this.score = main.getScore();
                this.elapsedTime = main.getElapsedTime();
            }
        }
    }

    public State getState() {
        return this.state;
    }

    public int getScore() {
        return this.score;
    }

    public int getElapsedTime() {
        return this.elapsedTime;
    }
}
