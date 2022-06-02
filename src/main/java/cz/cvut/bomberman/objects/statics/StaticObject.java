package cz.cvut.bomberman.objects.statics;

import cz.cvut.bomberman.game.Handler;
import cz.cvut.bomberman.objects.MainObject;

import java.awt.*;

/**
 * Class StaticObject extends MainObject. Its used for determining static objects.
 */
public abstract class StaticObject extends MainObject {
    public boolean isDestroyable; // True for items with unlimited life span

    public static final int DEFAULT_OBJECT_WIDTH = 50;

    public static final int DEFAULT_OBJECT_HEIGHT = 50;

    public StaticObject(float x, float y, int width, int height) {
        super(x, y, width, height);
    }


    @Override
    public void update() {

    }

    @Override
    public void render(Graphics graphics) {

    }
}
