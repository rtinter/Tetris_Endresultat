import java.util.Random;

public enum BlockType {
    I, O, T, L, J, Z, S;

    private static Random random = new Random();

    public static BlockType random() {
        int randomIndex = random.nextInt(values().length);
        return values()[randomIndex];
    }
}
