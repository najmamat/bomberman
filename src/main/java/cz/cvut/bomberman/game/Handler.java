package cz.cvut.bomberman.game;

import cz.cvut.bomberman.game.map.GameMap;
import cz.cvut.bomberman.objects.KeyboardControls;
/**
 * Class Handler is a class for handling data.
 */
public class Handler {
    // class for handling data
    private Game game;
    private GameMap map;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    public Handler(Game game){
        this.game = game;
    }

    public int getWidth(){
        return game.getWidth();
    }

    public int getHeight(){
        return game.getHeight();
    }

    public KeyboardControls getPlayerControls(){
        return game.getPlayerControls();
    }
}
