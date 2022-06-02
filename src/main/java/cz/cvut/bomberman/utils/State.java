package cz.cvut.bomberman.utils;

import cz.cvut.bomberman.game.Game;
import cz.cvut.bomberman.game.Handler;
import cz.cvut.bomberman.game.map.GameMap;

import java.awt.*;

/**
 * Parent class State for our states. Used only in GameState at the moment. TODO: Delete this class
 */
public abstract class State {

    // State Manager (Could be separate class)
    private static State currState = null;

    public static void setState(State state){
        currState = state;
    }

    public static State getState(){
        return currState;
    }
    // ----------

    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract void update();

    public abstract void render(Graphics graphics);

}
