package cz.cvut.bomberman.game.states;

import cz.cvut.bomberman.game.Handler;
import cz.cvut.bomberman.game.map.GameMap;
import cz.cvut.bomberman.objects.Player;
import cz.cvut.bomberman.objects.statics.Bomb;
import cz.cvut.bomberman.utils.State;

import java.awt.*;

/**
 * GameState class is a remnant of the first idea to make State classes and then only change states to get in between
 * Menu, Game, Settings etc. We abolished that idea.
 */
public class GameState extends State {

    public GameMap gameMap;

    public GameState(Handler handler, String path, int numberOfPlayers, int playerHealth){
        super(handler);
        gameMap = new GameMap(handler, path, numberOfPlayers, playerHealth);
        handler.setMap(gameMap);
    }

    @Override
    public void update() {
        gameMap.update();
    }

    @Override
    public void render(Graphics graphics) {
        gameMap.render(graphics);
    }
}
