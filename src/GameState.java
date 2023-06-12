/**
 * Die GameState-Klasse repräsentiert den aktuellen Zustand des Spiels.
 * Sie verwaltet die Zustandsübergänge des Spiels und bietet Methoden zur Steuerung des Spielverlaufs.
 */
public class GameState extends Kollision {

    /**
     * Aufzählungstyp, der die möglichen Zustände des Spiels darstellt.
     */
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

    /**
     * Konstruktor für ein GameState-Objekt mit der angegebenen Main-Instanz, Anzahl der Reihen und Anzahl der Spalten.
     *
     * @param main Die Main-Instanz, die das Spiel steuert
     * @param rows Die Anzahl der Reihen im Spielfeld
     * @param cols Die Anzahl der Spalten im Spielfeld
     */
    public GameState(Main main, int rows, int cols) {
        super(rows + 1, cols);
        this.main = main;
        this.state = State.RUNNING;
        this.score = 0;
        this.elapsedTime = 0;
    }

    /**
     * Setzt den Spielzustand auf den Startzustand.
     */
    public void startScreen() {
        this.state = State.START;
    }

    /**
     * Startet das Spiel und setzt den Spielzustand auf "Running".
     * Ruft die setup-Methode der Main-Instanz auf und setzt den Timer zurück.
     */
    public void startGame() {
        this.state = State.RUNNING;
        main.setup();
        main.resetTimerStart();
    }

    /**
     * Pausiert das Spiel, wenn es sich im "Running"-Zustand befindet.
     * Setzt den Spielzustand auf "Paused" und speichert die aktuelle Zeit.
     */
    public void pauseGame() {
        if (this.state == State.RUNNING) {
            this.state = State.PAUSED;
            main.pauseTime = main.millis();
        }
    }

    /**
     * Setzt das Spiel fort, wenn es sich im "Paused"-Zustand befindet.
     * Setzt den Spielzustand auf "Running" und aktualisiert den Timerstart basierend auf der vergangenen Pausenzeit.
     */
    public void resumeGame() {
        if (this.state == State.PAUSED) {
            this.state = State.RUNNING;
            main.timerStart += main.millis() - main.pauseTime;
        }
    }

    /**
     * Beendet das Spiel und setzt den Spielzustand auf "Game Over".
     */
    public void gameOver() {
        this.state = State.GAME_OVER;
    }

    /**
     * Gibt den aktuellen Zustand des Spiels zurück.
     *
     * @return Der aktuelle Zustand des Spiels
     */
    public State getState() {
        return this.state;
    }
}