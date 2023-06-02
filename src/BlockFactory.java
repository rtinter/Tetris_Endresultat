import java.util.ArrayList;
import java.util.Random;

public class BlockFactory {
    private ArrayList<Block> blocks;
    private Random random;
    private Block nextBlock;

    public BlockFactory() {
        blocks = new ArrayList<>();
        blocks.add(new I_Block());
        blocks.add(new J_Block());
        blocks.add(new L_Block());
        blocks.add(new O_Block());
        blocks.add(new S_Block());
        blocks.add(new T_Block());
        blocks.add(new Z_Block());

        random = new Random();
        nextBlock = randomBlock();
    }

    public Block getNextBlock() {
        Block block = nextBlock;

        do {
            nextBlock = randomBlock();
        } while (block.getId() == nextBlock.getId());

        return block;
    }

    private Block randomBlock() {
        int randomIndex = random.nextInt(blocks.size());
        return blocks.get(randomIndex);
    }
}
