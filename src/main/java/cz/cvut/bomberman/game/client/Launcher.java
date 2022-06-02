package cz.cvut.bomberman.game.client;

/**
 * Launcher is the class for running our app.
 */

public class Launcher {
    private static final int DEFAULT_FRAME_WIDTH = 1200;
    private static final int DEFAULT_FRAME_HEIGHT = 950;

    /**
     * We have StartGame() inside main, it leads to the class with first UI with menu.
     * @param args
     */
    public static void main(String[] args){
        new StartGame(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
    }
}

