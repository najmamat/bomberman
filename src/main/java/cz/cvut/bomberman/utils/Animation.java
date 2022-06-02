package cz.cvut.bomberman.utils;

import java.awt.image.BufferedImage;

/**
 * Class to animate our player. Its gets a speed of the Animation and BufferedImageArray. It determines how BufferedImages in the list
 * will be rendered.
 */
public class Animation {

    private int speed, index;
    private BufferedImage[] array;
    private long timer, lastTime;

    public Animation(int speed, BufferedImage[] array){
        this.speed = speed;
        this.array = array;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public BufferedImage getCurrImage(){
        return array[index];
    }

    public void update(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed) {
            index++;
            timer = 0;
            if (index >= array.length){
                index = 0;
            }
        }
    }
}
