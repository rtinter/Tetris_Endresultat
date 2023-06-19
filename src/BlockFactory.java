import java.util.Random;

/**
 * Singleton-Klasse BlockFactory zum Erstellen und Verwalten von Block-Objekten.
 * Sie behält auch den aktuellen und nächsten Block des Spiels bei.
 */
public class BlockFactory {

    /**
     * Singleton Instanz von BlockFactory.
     */
    private static BlockFactory _blockFactory;

    /**
     * Zufallsgenerator zur Erzeugung von zufälligen Blocktypen.
     */
    private final Random random = new Random();

    /**
     * Der aktuelle Block im Spiel.
     */
    private Block currentBlock;

    /**
     * Der nächste Block, der ins Spiel kommt.
     */
    private Block nextBlock;

    /**
     * Das Spielfeld, auf dem die Blöcke platziert werden.
     */
    private GameGrid _gameGrid;

    /**
     * Privater Konstruktor, um die Singleton-Instanz zu initialisieren.
     * @param grid Das Spielfeld, auf dem die Blöcke platziert werden.
     */
    private BlockFactory(GameGrid grid) {
        _gameGrid = grid;
        currentBlock = getRandomBlock();
        nextBlock = getRandomBlock();
    }

    /**
     * Initialisiert die BlockFactory Singleton Instanz.
     * @param grid Das Spielfeld, auf dem die Blöcke platziert werden.
     * @return Die Singleton Instanz von BlockFactory.
     */
    public static BlockFactory initBlockFactory(GameGrid grid){
        if(_blockFactory != null){
            throw new AssertionError("BlockFactory has already been initialized.");
        }
        // call private constructor to init singleton
        _blockFactory = new BlockFactory(grid);
        return _blockFactory;
    }

    /**
     * Liefert die Singleton Instanz von BlockFactory.
     * @return Die Singleton Instanz von BlockFactory.
     */
    public static BlockFactory getInstance(){
        if(_blockFactory == null){
            throw new AssertionError("BlockFactory has not been initialized.");
        }
        return _blockFactory;
    }

    /**
     * Gibt den nächsten Block im Spiel zurück.
     * @return Der nächste Block.
     */
    public Block blockQueue() {
        return nextBlock;
    }

    /**
     * Initialisiert den nächsten Block im Spiel.
     * @return Der neu erstellte Block.
     */
    public Block initNextBlock() {
        return getBlock();
    }

    /**
     * Gibt den aktuellen Block im Spiel zurück.
     * @return Der aktuelle Block.
     */
    public Block getCurrentBlock(){
        return this.currentBlock;
    }

    /**
     * Setzt den aktuellen Block im Spiel.
     * @param block Der Block, der als aktueller Block gesetzt werden soll.
     */
    public void setCurrentBlock(Block block){
        this.currentBlock = block;
    }

    /**
     * Setzt den nächsten Block im Spiel.
     * @param block Der Block, der als nächster Block gesetzt werden soll.
     */
    public void setNextBlock(Block block){
        this.nextBlock = block;
    }

    /**
     * Erzeugt einen zufälligen Blocktyp.
     * @return Der neu erstellte Block.
     */
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
     * Erzeugt einen zufälligen Block.
     * @return Der neu erstellte Block.
     */
    private Block getRandomBlock() {
        return getBlock();
    }
}