package cz.cvut.bomberman.objects.statics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class Block is a foundation for our map, that is generated from blocks. Other Classes like BrickBlock extends this class.
 */
public class Block {

    private boolean isWall = true;

    // Class
    public static Block[] blocks = new Block[512];
    public static Block brickBlock = new BrickBlock(0);
    public static Block stoneBlock = new StoneBlock(1);
    public static Block planksBlock = new PlanksBlock(2);
    // end of class

    public static final int BLOCK_WIDTH = 75;
    public static final int BLOCK_HEIGHT = 75;
    public Rectangle collisionRec;

    protected BufferedImage texture;
    protected int id;

    public Block(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id;
        blocks[id] = this;
    }

    public void update(){

    }

    public void render(Graphics graphics, int x, int y){
        graphics.drawImage(texture, x, y, BLOCK_WIDTH, BLOCK_HEIGHT, null);
    }

    public boolean isWall(){
        return isWall;
    }

    public int getId(){
        return id;
    }

    public void changeTexture(BufferedImage t){
        texture = t;
    }

    public void changeWall(boolean ch){
        isWall = ch;
    }
}
