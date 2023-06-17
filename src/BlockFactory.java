import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Die Klasse BlockFactory ist für die Erzeugung und Verwaltung von Blöcken im Spiel zuständig.
 */
public class BlockFactory {

    private static BlockFactory _blockFactory;

    // private final List<Block> blocks = new ArrayList<>(); // Die Liste der verfügbaren Blöcke
    private final Random random = new Random(); // Der Zufallsgenerator

    private Block currentBlock = null; // Der aktuelle Block
    private Block nextBlock = null; // Der nächste Block

    private GameGrid _gameGrid;

    /**
     * Konstruktor für die BlockFactory-Klasse.
     * Initialisiert die Liste der Blöcke, den Zufallsgenerator und den aktuellen und nächsten Block.
     */
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

    /**
     * Gibt den nächsten Block in der Blockwarteschlange zurück, ohne ihn zu ändern.
     *
     * @return Der nächste Block in der Blockwarteschlange.
     */
    public Block blockQueue() {
        return nextBlock;
    }

    /**
     * Gibt den nächsten Block in der Blockwarteschlange zurück und aktualisiert den nächsten Block.
     *
     * @return Der nächste Block in der Blockwarteschlange.
     */
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

    /**
     * Wählt einen zufälligen Block aus der Liste der verfügbaren Blöcke aus.
     *
     * @return Ein zufälliger Block aus der Liste der verfügbaren Blöcke.
     */
    private Block getRandomBlock() {
        return getBlock();
    }
}