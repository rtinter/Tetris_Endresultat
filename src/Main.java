import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Random;


import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Random;

public class Main extends PApplet {

    public static void main(String[] args) {
        PApplet.main(Main.class);
    }

    GameGrid gridPlayground = new GameGrid(20, 10);
    GameGrid gridNextStone = new GameGrid(4, 4);

    ArrayList<Block> blocks = new ArrayList<>();

    Block currentBlock;

    @Override
    public void settings() {
        size(600, 780);

        blocks.add(new I_Block());
        blocks.add(new L_Block());
        blocks.add(new O_Block());
        blocks.add(new S_Block());
        blocks.add(new J_Block());
        blocks.add(new T_Block());
        blocks.add(new Z_Block());

        // Zufälliges Objekt auswählen
        int randomIndex = (int) (Math.random() * blocks.size());
        currentBlock = blocks.get(randomIndex);

        // Block im gridNextStone zeichnen
        gridNextStone.drawBlock(currentBlock.getTiles(), currentBlock.getStartOffset(), currentBlock.getId());

        // Block im gridNextStone zeichnen
        gridPlayground.drawBlock(currentBlock.getTiles(), currentBlock.getStartOffset(), currentBlock.getId());

    }

    @Override
    public void draw() {
        background(255);

        // gridPlayground zeichnen
        gridPlayground.draw(this);

        // gridNextStone zeichnen
        translate(380, 0);
        gridNextStone.draw(this);
    }
}
