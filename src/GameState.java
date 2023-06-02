import processing.core.PApplet;
import java.util.ArrayList;


public class GameState extends GameGrid {
    private boolean running;
    private Block currentBlock;

    public GameState(int rows, int cols) {
        super(rows, cols);
        running = false;
        currentBlock = null;
    }

    public void startGame() {
        running = true;
        spawnNextBlock();
    }

    public void stopGame() {
        running = false;
        currentBlock = null;
    }

    public void spawnNextBlock() {
        if (!running) return;

        ArrayList<Block> blocks = createBlocks();
        int randomIndex = (int) (Math.random() * blocks.size());
        currentBlock = blocks.get(randomIndex);
    }

    @Override
    public void draw(PApplet a) {
        super.draw(a);

        if (currentBlock != null) {
            int[][][] blockPositions = currentBlock.getTiles();
            int[] startOffset = currentBlock.getStartOffset();
            int blockId = currentBlock.getId();

            drawBlock(blockPositions, startOffset, blockId);
        }
    }

    private ArrayList<Block> createBlocks() {
        ArrayList<Block> blocks = new ArrayList<>();
        blocks.add(new I_Block());
        blocks.add(new L_Block());
        blocks.add(new O_Block());
        blocks.add(new S_Block());
        blocks.add(new J_Block());
        blocks.add(new T_Block());
        blocks.add(new Z_Block());
        return blocks;
    }


}
