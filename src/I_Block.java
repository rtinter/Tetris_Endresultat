 public class I_Block extends Block{

        private final int[][][] tiles = createTiles();


     protected int[][][] createTiles() {
            int[][][] tiles = new int[4][4][2];// 1. Dimension = Rotationstate, 2. Dimension = Position der Tiles im Objekt, 3. Dimension = x- und y-Koordinaten
            tiles[0] = new int[][] { { 1, 0 }, { 1, 1 }, { 1, 2 }, { 1, 3 } };
            tiles[1] = new int[][] { { 0, 2 }, { 1, 2 }, { 2, 2 }, { 3, 2 } };
            tiles[2] = new int[][] { { 2, 0 }, { 2, 1 }, { 2, 2 }, { 2, 3 } };
            tiles[3] = new int[][] { { 0, 1 }, { 1, 1 }, { 2, 1 }, { 3, 1 } };
            return tiles;
        }

        public int getId() {
            return 1;
        }

        protected int[] getStartOffset() {
            return new int[] { 0, 0 };
        }

        public int[][][] getTiles() {
            return tiles;
        }


}

