import processing.core.PApplet;
import java.util.ArrayList;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main(Main.class);
    }

    public static final int speed = 50;

    BlockFactory blockFactory = new BlockFactory();

    InputHandler input = new InputHandler();

    GameGrid gridPlayground = new GameGrid(20, 10);
    GameGrid gridNextStone = new GameGrid(4, 4);

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

        BlockFactory blockFactory = new BlockFactory();



        // gridPlayground zeichnen
        gridPlayground.draw(this);

        // gridNextStone zeichnen
        translate(380, 0);
        gridNextStone.draw(this);

        if (frameCount % speed == 0) {


            // Löscht den aktuellen Block bevor er sich bewegt
            gridPlayground.deleteBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition());

            // Bewegt den Block down
            boolean movedDown = currentBlock.moveDown(gridPlayground);

            // Überprüft, ob der Block erfolgreich nach unten bewegt wurde
            if (!movedDown) {
                // Block konnte nicht nach unten bewegt werden, daher wird er eingefroren
                currentBlock.freeze(gridPlayground);

                // Erzeugt einen neuen Block
                currentBlock = blockFactory.getNextBlock();
                System.out.println(" block: " + currentBlock.getId());
            }


        }
            // Zeichnet den Block nach dem Bewegen
            gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getCurrentPosition(), currentBlock.getId());

      }

}

