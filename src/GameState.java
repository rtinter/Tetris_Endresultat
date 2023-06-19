/**
 * Die GameState Klasse verwaltet den Zustand des Spiels.
 */
public class GameState {

    private GridController gridController;  // Kontrolliert das Spielfeld


    /**
     * Enum, das die möglichen Zustände des Spiels darstellt.
     */
    public enum State {
        START,      // Das Spiel befindet sich im Startzustand
        RUNNING,    // Das Spiel läuft
        PAUSED,     // Das Spiel ist pausiert
        GAME_OVER   // Das Spiel ist vorbei
    }


    private State state;  // Der aktuelle Zustand des Spiels

    private final Main main;  // Main-Instanz, die das Spiel steuert

    private final int score;  // Aktueller Punktestand

    private final int elapsedTime;  // Vergangene Spielzeit (in ms)


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
