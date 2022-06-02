package cz.cvut.bomberman.objects;

import java.awt.*;

/**
 * Class MainObject is the main class for all objects. Not for blocks. Blocks are not considered objects in this game.
 * It has parameters that all objects in our game inherit, such as coordinates, width, height, collisionRectangle.
 */
public abstract class MainObject {
    protected float x, y;
    protected  int width, height;
    public Rectangle collisionRec;

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public MainObject(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        collisionRec = new Rectangle(0, 0, width, height);

    }

    public abstract void update();

    public abstract void render(Graphics graphics);

}
