package cz.cvut.bomberman.utils;

import java.awt.image.BufferedImage;

/**
 * Simple class SpriteSheet to distinguish between our texture images inside SpriteSheet.
 */
public class SpriteSheet {
    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }

    public BufferedImage getImage(int x, int y, int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }
}
