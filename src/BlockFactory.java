import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockFactory {
    private List<Block> blocks;
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
        nextBlock = randomBlock();

        return block;
    }

    private Block randomBlock() {
        Block randomBlock;
        do {
            randomBlock = blocks.get(random.nextInt(blocks.size()));
        } while (randomBlock == nextBlock);

        return randomBlock;
    }
}
