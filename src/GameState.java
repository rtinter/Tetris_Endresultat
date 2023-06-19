/**
 * Die GameState Klasse verwaltet den Zustand des Spiels.
 */
public class GameState {

    /**
     * Kontrolliert das Spielfeld.
     */
    private GridController gridController;

    /**
     * Enum, das die möglichen Zustände des Spiels darstellt.
     */
    public enum State {
        START,      // Das Spiel befindet sich im Startzustand
        RUNNING,    // Das Spiel läuft
        PAUSED,     // Das Spiel ist pausiert
        GAME_OVER   // Das Spiel ist vorbei
    }

    /**
     * Der aktuelle Zustand des Spiels.
     */
    private State state;

    /**
     * Die Main-Instanz, die das Spiel steuert.
     */
    private final Main main;

    /**
     * Der aktuelle Punktestand des Spiels.
     */
    private final int score;

    /**
     * Die vergangene Spielzeit in Millisekunden.
     */
    private final int elapsedTime;

    /**
     * Konstruktor für die GameState Klasse.
     * @param main Die Main-Instanz, die das Spiel steuert.
     * @param rows Anzahl der Zeilen im Spielfeld.
     * @param cols Anzahl der Spalten im Spielfeld.
     */
    public GameState(Main main, int rows, int cols) {
        this.gridController = new GridController(main, rows + 1, cols);
        this.main = main;
        this.state = State.RUNNING;
        this.score = 0;
        this.elapsedTime = 0;
    }

    /**
     * Wechselt das Spiel in den Startbildschirm Zustand.
     */
    public void startScreen() {
        this.state = State.START;
    }

    /**
     * Startet das Spiel.
     */
    public void startGame() {
        this.state = State.RUNNING;
        main.setup();
        main.resetTimerStart();
    }

    /**
     * Pausiert das Spiel, wenn es gerade läuft.
     */
    public void pauseGame() {
        if (this.state == State.RUNNING) {
            this.state = State.PAUSED;
            main.pauseTime = main.millis();
        }
    }

    /**
     * Setzt das Spiel fort, wenn es pausiert ist.
     */
    public void resumeGame() {
        if (this.state == State.PAUSED) {
            this.state = State.RUNNING;
            main.timerStart += main.millis() - main.pauseTime;
        }
    }

    /**
     * Beendet das Spiel.
     */
    public void gameOver() {
        this.state = State.GAME_OVER;
    }

    /**
     * Gibt den aktuellen Zustand des Spiels zurück.
     * @return Der aktuelle Spielzustand.
     */
    public State getState() {
        return this.state;
    }

}
