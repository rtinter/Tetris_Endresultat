import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockFactory {
    private final List<Block> blocks;
    private final Random random;
    private final Block currentBlock;
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
        blocks.add(new I_Block());
        blocks.add(new J_Block());
        blocks.add(new L_Block());
        blocks.add(new O_Block());
        blocks.add(new S_Block());
        blocks.add(new T_Block());
        blocks.add(new Z_Block());

        random = new Random();
        currentBlock = getRandomBlock();
        nextBlock = getRandomBlock();
    }

public Block blockQueue() {
            return nextBlock;
        }


    public Block getNextBlock() {
        Block block = nextBlock;
        nextBlock = getRandomBlock();
        return block;
    }

    private Block getRandomBlock() {
        return blocks.get(random.nextInt(blocks.size()));
    }
}

