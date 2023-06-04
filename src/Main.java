import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main(Main.class);
    }

    public static final int speed = 50;

    BlockFactory blockFactory = new BlockFactory();
    Block nextBlockInQueue = blockFactory.blockQueue();


    GameGrid gridPlayground = new GameGrid(20, 10);
    GameGrid gridNextStone = new GameGrid(4, 4);

    Block currentBlock;

    int score = 0;

    int timerStart;

    int elapsedSeconds;

    int pauseTime;

    GameState gameState;




    @Override
    public void settings() {
        size(600, 780);

        timerStart = millis();

        currentBlock = blockFactory.getNextBlock();
        nextBlockInQueue = blockFactory.blockQueue();


        // Alle Zellen mit 0 füllen
        gridPlayground.setupGrid(gridPlayground);

        //Block im gridNextStone zeichnen
        gridNextStone.drawBlock(nextBlockInQueue.getTiles(), nextBlockInQueue.startCoordsForNextBlock(), nextBlockInQueue.getId(), nextBlockInQueue.currentRotation);

        gameState = new GameState(this);

        gameState.startScreen();
    }

    @Override
    public void draw() {

        if(gameState.getState() == GameState.State.START) {
            background(255);
            textSize(60);
            fill(0);
            textAlign(CENTER, CENTER);
            text("TETRIS", width/2, height/2 - 40);
            textSize(24);
            text("Press 'Enter' to begin", width/2, height/2 + 20);
        }

        //
        if(gameState.getState() == GameState.State.RUNNING) {


        // Update elapsedSeconds by subtracting the timer start time from the current time and dividing by 1000 to get seconds.
        elapsedSeconds = (millis() - timerStart) / 1000;

        background(255);

        BlockFactory blockFactory = new BlockFactory();


        // gridPlayground zeichnen
        gridPlayground.draw(this);

        // gridNextStone zeichnen
            pushMatrix(); //Speichern des aktuellen Matrix-Zustands (sonst verschiebt sich die Score/Timer Anzeige)
        translate(380, 0);
        gridNextStone.draw(this);
            popMatrix(); //Wiederherstellen des letzen Matrix-Zustands (sonst verschiebt sich die Score/Timer Anzeige)


        if (frameCount % speed == 0) {

            // Löscht den aktuellen Block bevor er sich bewegt
            gridPlayground.deleteBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.currentRotation);

            // Bewegt den Block nach unten
            boolean movedDown = currentBlock.moveDown(gridPlayground);

            // Überprüft, ob der Block erfolgreich nach unten bewegt wurde
            if (!movedDown) {
                // Block konnte nicht nach unten bewegt werden, daher wird er eingefroren
                currentBlock.freeze(gridPlayground);

                int points = gridPlayground.clearFullRows(); // Punkte zuweisen
                score += points; // Punkte erhöhen

                gridNextStone.setupGrid(gridNextStone);

                // Erzeugt einen neuen Block
                currentBlock = nextBlockInQueue;

                // Den darauffolgenden im nextblockfeld anzeigen lassen
                nextBlockInQueue = blockFactory.getNextBlock();
                gridNextStone.drawBlock(nextBlockInQueue.getTiles(), nextBlockInQueue.startCoordsForNextBlock(), nextBlockInQueue.getId(), nextBlockInQueue.currentRotation);
            }
        }


        // Zeichnet den Block nach jedem Tick neu
        gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId(), currentBlock.currentRotation);

        // Zeigt den Punktestand
        fill(0);
        textSize(24);
        text("Score: " + score, 480, 300); //x-Koordinate wurde verändert, da sie nach Spielstart verschoben wurde

        //Zeigt die vergangene Zeit
        fill(0);
        textSize(24);
        text("Time: " + elapsedSeconds, 480, 350); //x-Koordinate wurde verändert, da sie nach Spielstart verschoben wurde
      }
        // Bei Pausierung: "PAUSE"
        else if(gameState.getState() == GameState.State.PAUSED) {
            //background(255);
            textSize(60);
            fill(255,0,0);
            textAlign(CENTER, CENTER);
            text("PAUSE", width/2, height/2);
        }
    }


    public void keyPressed() {
        switch(keyCode) {
            case LEFT: // Bewegt den Block nach links, wenn die linke Pfeiltaste gedrückt wird
                gridPlayground.deleteBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.currentRotation);
                currentBlock.moveLeft(gridPlayground);
                gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId(), currentBlock.currentRotation);
                break;

            case RIGHT: // Bewegt den Block nach rechts, wenn die rechte Pfeiltaste gedrückt wird
                gridPlayground.deleteBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.currentRotation);
                currentBlock.moveRight(gridPlayground);
                gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId(), currentBlock.currentRotation);
                break;

            case DOWN: // Bewegt den Block nach unten, wenn die untere Pfeiltaste gedrückt wird
                gridPlayground.deleteBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.currentRotation);
                currentBlock.moveDown(gridPlayground);
                gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId(), currentBlock.currentRotation);
                break;

            case UP:
                gridPlayground.deleteBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(),currentBlock.currentRotation);
                currentBlock.rotate(gridPlayground);
                gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId(), currentBlock.currentRotation);
                break;

            default:
                break;
        }

        // Tasteneingabe um das Spiel zu starten
        if(key == ENTER) {
            if(gameState.getState() == GameState.State.START) {
                gameState.startGame(); // Spiel starten, wenn es gestartet wird
            }
        }

        //Tasteneingabe zum pausieren des Spiels
        if(key == ' ') { // Wenn die Leertaste gedrückt wird
            if(gameState.getState() == GameState.State.RUNNING) {
                gameState.pauseGame(); // Spiel pausieren, wenn es läuft
            } else if(gameState.getState() == GameState.State.PAUSED) {
                gameState.resumeGame(); // Spiel fortsetzen, wenn es pausiert ist
            }
        }
    }

    //Für GameState
    public int getScore() {
        return this.score;
    }

    public int getElapsedTime() {
        return this.elapsedSeconds;
    }

    public void resetTimerStart() {
        this.timerStart = millis();
    }



}