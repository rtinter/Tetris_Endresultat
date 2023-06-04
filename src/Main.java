import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main(Main.class);
    }

    public static final int speed = 50;


    GameGrid gridPlayground = new GameGrid(20, 10);
    GameGrid gridNextStone = new GameGrid(4, 4);

    BlockFactory blockFactory = new BlockFactory();

    Block currentBlock;

    @Override
    public void settings() {
        size(600, 780);

        currentBlock = blockFactory.getNextBlock();


        // Alle Zellen mit 0 füllen
        gridPlayground.setupGrid(gridPlayground);

        // Block im gridNextStone zeichnen
       //gridNextStone.drawBlock(currentBlock.getTiles(), currentBlock.startCoords(), currentBlock.getId());

        // Block im gridNextStone zeichnen
        gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.startCoords(), currentBlock.getId());

    }

    @Override
    public void draw() {
        background(255);

        // gridPlayground zeichnen
        gridPlayground.draw(this);

        // gridNextStone zeichnen
        translate(380, 0);
        gridNextStone.draw(this);


        gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId());

        if (frameCount % speed == 0) {

            // Löscht den aktuellen Block bevor er sich bewegt
            gridPlayground.deleteBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition());

            // Bewegt den Block down
            currentBlock.moveDown(gridPlayground);

            // Zeichnet den Block nach dem Bewegen
            gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId());
        }


    }

    @Override
    public void keyPressed() {
        switch(keyCode) {
            case LEFT: // Bewegt den Block nach links, wenn die linke Pfeiltaste gedrückt wird
                gridPlayground.deleteBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition());
                currentBlock.moveLeft(gridPlayground);
                gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId());
                break;
            case RIGHT: // Bewegt den Block nach rechts, wenn die rechte Pfeiltaste gedrückt wird
                gridPlayground.deleteBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition());
                currentBlock.moveRight(gridPlayground);
                gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId());
                break;
            case DOWN: // Bewegt den Block nach unten, wenn die untere Pfeiltaste gedrückt wird
                gridPlayground.deleteBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition());
                currentBlock.moveDown(gridPlayground);
                gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId());
                break;
            default:
                break;
        }
    }



}
