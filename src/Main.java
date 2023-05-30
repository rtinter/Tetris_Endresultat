import processing.core.PApplet;
import java.util.ArrayList;
import java.util.Random;


public class Main extends PApplet{

    public static void main(String[] args) {
        PApplet.main(Main.class);
    }


    GameGrid gridPlayground = new GameGrid(20,10);
    GameGrid gridNextStone = new GameGrid(4,4);



    ArrayList<Block> blocks = new ArrayList<>();





    //j = rotation -> hier 0
    //i = eins der elemente mit den koordinaten als inhalt
    // 1. wert gibt die zeile an und der 2. die spalte
    // startoffset 0 und eins sind die werte der startposition => Definiert im block
   /* public void drawBlock(J_Block block, int[][][] blockPositions, int[] startOffset) {
        for (int i = 0, j = 0; i < blockPositions[j].length; i++) {
            int row = blockPositions[j][i][0] + startOffset[0];
            int col = blockPositions[j][i][1] + startOffset[1];
            gridPlayground.setPosition(row, col, block.getId());
        }
    }*/

    

    @Override
    public void settings(){
        size(600,780);

        blocks = new ArrayList<>();
        blocks.add(new I_Block());
        blocks.add(new L_Block());
        blocks.add(new O_Block());
        blocks.add(new S_Block());
        blocks.add(new J_Block());
        blocks.add(new T_Block());
        blocks.add(new Z_Block());

        // Zufälliges Objekt auswählen
        int randomIndex = (int) (Math.random() * blocks.size());
        Block randomBlock = blocks.get(randomIndex);

        //Block malen
        gridPlayground.drawBlock(randomBlock.getTiles(), randomBlock.getStartOffset(), randomBlock.getId() );

    }

    @Override
    public void draw() {

        gridPlayground.draw(this);
        translate(380,0);
        gridNextStone.draw(this);


    }


}


