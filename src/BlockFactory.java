import java.util.Random;


public class BlockFactory {

    private static BlockFactory _blockFactory;

    private final Random random = new Random();

    private Block currentBlock;
    private Block nextBlock;

    private GameGrid _gameGrid;


    private BlockFactory(GameGrid grid) {
        _gameGrid = grid;
        currentBlock = getRandomBlock();
        nextBlock = getRandomBlock();
    }

    public static BlockFactory initBlockFactory(GameGrid grid){
        if(_blockFactory != null){
            throw new AssertionError("BlockFactory has already been initialized.");
        }
        // call private constructor to init singleton
        _blockFactory = new BlockFactory(grid);
        return _blockFactory;
    }

    public static BlockFactory getInstance(){
        if(_blockFactory == null){
            throw new AssertionError("BlockFactory has not been initialized.");
        }
        return _blockFactory;
    }

    public Block blockQueue() {
        return nextBlock;
    }


    public Block initNextBlock() {
        return getBlock();
    }

    public Block getCurrentBlock(){
        return this.currentBlock;
    }

    public void setCurrentBlock(Block block){
        this.currentBlock = block;
    }

    public void setNextBlock(Block block){
        this.nextBlock = block;
    }

    public Block getBlock(){
        int rnd = random.nextInt(7);
        Block block = null;
        switch (rnd){
            case 0 -> {
                block = new I_Block(this._gameGrid);
            }
            case 1 -> {
                block = new J_Block(this._gameGrid);
            }
            case 2 -> {
                block = new O_Block(this._gameGrid);
            }
            case 3 -> {
                block = new L_Block(this._gameGrid);
            }
            case 4 -> {
                block = new S_Block(this._gameGrid);
            }
            case 5 -> {
                block = new T_Block(this._gameGrid);
            }
            case 6 -> {
                block = new Z_Block(this._gameGrid);
            }
        }
        return block;
    }

    private Block getRandomBlock() {
        return getBlock();
    }
}