/**
 * Verwaltet Tastatureingaben im Spiel.
 */
public class KeyHandler {
    /**
     * Die Instanz des Hauptspiels (Main).
     */
    private final Main main;

    /**
     * Erstellt einen KeyHandler.
     *
     * @param main Die Instanz des Hauptspiels (Main)
     */
    public KeyHandler(Main main) {
        this.main = main;
    }

    /**
     * Verarbeitet Tastendruck-Ereignisse.
     * Handhabt die Bewegung und Rotation des aktuellen Blocks, sowie die Spielsteuerung
     * wie Start, Pause, Fortsetzen und Neustart des Spiels.
     */
    public void handleKeyPressed() {
        var currentBlock = BlockFactory.getInstance().getCurrentBlock();
        switch (main.keyCode) {
            case Main.LEFT -> { // Bewegt den Block nach links
                main.gridPlayground.deleteBlock(currentBlock);
                currentBlock.moveLeft();
                main.gridPlayground.drawBlock(currentBlock);
                return;
            }
            case Main.RIGHT -> { // Bewegt den Block nach rechts
                main.gridPlayground.deleteBlock(currentBlock);
                currentBlock.moveRight();
                main.gridPlayground.drawBlock(currentBlock);
                return;

            }
            case Main.DOWN -> { // Bewegt den Block nach unten
                main.gridPlayground.deleteBlock(currentBlock);
                currentBlock.moveDown();
                main.gridPlayground.drawBlock(currentBlock);
                return;

            }
            case Main.UP -> { // Rotiert den Block um 90 Grad
                main.gridPlayground.deleteBlock(currentBlock);
                currentBlock.rotate();
                main.gridPlayground.drawBlock(currentBlock);
                return;

            }
            default -> {
            }
        }

        // Tasteneingabe um das Spiel zu starten
        if (main.key == Main.ENTER) {
            if (main.gameState.getState() == GameState.State.START) {
                main.gameState.startGame();
            }
        }

        // Tasteneingabe zum Pausieren des Spiels
        if (main.key == ' ') {
            if (main.gameState.getState() == GameState.State.RUNNING) {
                main.gameState.pauseGame();
            } else if (main.gameState.getState() == GameState.State.PAUSED) {
                main.gameState.resumeGame();
            }
        }

        // Tasteneingabe zum Neustarten
        if (main.key == 'R' || main.key == 'r') {
            if (main.gameState.getState() == GameState.State.GAME_OVER) {
                main.resetGame();
                main.gameState.startGame();
            }
        }
    }
}