import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main(Main.class);
    }

    public static final int speed = 50;

    BlockFactory blockFactory = new BlockFactory();

    GameGrid gridPlayground = new GameGrid(20, 10);
    GameGrid gridNextStone = new GameGrid(4, 4);

    Block currentBlock;

    int score = 0;

    int timerStart;
    int elapsedSeconds;




    @Override
    public void settings() {
        size(600, 780);

        timerStart = millis();

        currentBlock = blockFactory.getNextBlock();

        // Alle Zellen mit 0 füllen
        gridPlayground.setupGrid(gridPlayground);

        //Block im gridNextStone zeichnen
       //gridNextStone.drawBlock(currentBlock.getTiles(), currentBlock.startCoords(), currentBlock.getId());

        // Block im gridNextStone zeichnen
        gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.startCoords(), currentBlock.getId(), currentBlock.currentRotation);

    }

    @Override
    public void draw() {

        // Update elapsedSeconds by subtracting the timer start time from the current time and dividing by 1000 to get seconds.
        elapsedSeconds = (millis() - timerStart) / 1000;

        background(255);

        BlockFactory blockFactory = new BlockFactory();



        // gridPlayground zeichnen
        gridPlayground.draw(this);

        // gridNextStone zeichnen
        translate(380, 0);
        gridNextStone.draw(this);


        if (frameCount % speed == 0) {


            // Löscht den aktuellen Block bevor er sich bewegt
            gridPlayground.deleteBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.currentRotation);

            // Bewegt den Block down
            boolean movedDown = currentBlock.moveDown(gridPlayground);

            // Überprüft, ob der Block erfolgreich nach unten bewegt wurde
            if (!movedDown) {
                // Block konnte nicht nach unten bewegt werden, daher wird er eingefroren
                currentBlock.freeze(gridPlayground);
                int points = gridPlayground.clearFullRows(); // punkte zuweisen
                gridPlayground.clearFullRows(); // Reihen löschen

                score += points; // Punkte erhöhen

                // Erzeugt einen neuen Block
                currentBlock = blockFactory.getNextBlock();
            }

        }
            // Zeichnet den Block nach jedem Tick neu
        gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId(), currentBlock.currentRotation);


        // Zeigt den Punktestand
        fill(0);
        textSize(24);
        text("Score: " + score, 40, 300);

        //Zeigt die vergangene Zeit
        fill(0);
        textSize(24);
        text("Time: " + elapsedSeconds, 40, 350);
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
    }


}