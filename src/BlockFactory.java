import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Die Klasse BlockFactory ist für die Erzeugung und Verwaltung von Blöcken im Spiel zuständig.
 */
public class BlockFactory {
    private final List<Block> blocks; // Die Liste der verfügbaren Blöcke
    private final Random random; // Der Zufallsgenerator
    private final Block currentBlock; // Der aktuelle Block
    private Block nextBlock; // Der nächste Block

    /**
     * Konstruktor für die BlockFactory-Klasse.
     * Initialisiert die Liste der Blöcke, den Zufallsgenerator und den aktuellen und nächsten Block.
     */
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
    public Block getNextBlock() {
        Block block = nextBlock;
        nextBlock = getRandomBlock();
        return block;
    }

    /**
     * Wählt einen zufälligen Block aus der Liste der verfügbaren Blöcke aus.
     *
     * @return Ein zufälliger Block aus der Liste der verfügbaren Blöcke.
     */
    private Block getRandomBlock() {
        return blocks.get(random.nextInt(blocks.size()));
    }
}