import processing.core.PApplet;

public class Main extends PApplet{

    public static void main(String[] args) {
        PApplet.main(Main.class);
    }


    GameGrid gridPlayground = new GameGrid(20,10);
    GameGrid gridNextStone = new GameGrid(4,4);

    @Override
    public void settings(){
        size(600,780);

    }

    @Override
    public void draw() {

        gridPlayground.draw(this);
        translate(380,0);
        gridNextStone.draw(this);


    }


}