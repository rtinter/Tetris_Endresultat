import processing.core.PApplet;

public class InputHandler {
    private boolean[] arrowKeysPressed;

    public InputHandler() {
        arrowKeysPressed = new boolean[4]; // 0: oben, 1: unten, 2: links, 3: rechts
    }

    public boolean isArrowKeyPressed(int arrowKey) {
        return arrowKeysPressed[arrowKey];
    }

    public void keyPressed(int keyCode) {
        if (keyCode == PApplet.UP) {
            arrowKeysPressed[0] = true;
        } else if (keyCode == PApplet.DOWN) {
            arrowKeysPressed[1] = true;
        } else if (keyCode == PApplet.LEFT) {
            arrowKeysPressed[2] = true;
        } else if (keyCode == PApplet.RIGHT) {
            arrowKeysPressed[3] = true;
        }
    }

    public void keyReleased(int keyCode) {
        if (keyCode == PApplet.UP) {
            arrowKeysPressed[0] = false;
        } else if (keyCode == PApplet.DOWN) {
            arrowKeysPressed[1] = false;
        } else if (keyCode == PApplet.LEFT) {
            arrowKeysPressed[2] = false;
        } else if (keyCode == PApplet.RIGHT) {
            arrowKeysPressed[3] = false;
        }
    }
}
