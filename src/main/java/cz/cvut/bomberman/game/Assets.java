package cz.cvut.bomberman.game;

import cz.cvut.bomberman.utils.SpriteSheet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class Assets loads our sprite sheet with all our pictures to render. It uses method getImage to separate images from each other.
 * We use sprite sheet, because our game then loads only 1 big picture at the start and then it runs smoothly.
 */
public class Assets {
    private static final int WIDTH = 150;
    private static final int HEIGHT = 150;

    public static BufferedImage stone, brick, planks;
    public static BufferedImage bomb, explosion;
    public static BufferedImage playerStaleLeft, playerStaleRight, alien;
    public static BufferedImage[] playerRunLeft, playerRunRight;


    public static void init() throws IOException {
        SpriteSheet sheet = new SpriteSheet(ImageIO.read(new File("resources/textures/sheet1.png")));

        stone = sheet.getImage(0, 0, WIDTH, HEIGHT);
        brick = sheet.getImage(WIDTH, 0, WIDTH, HEIGHT);
        bomb = sheet.getImage(WIDTH * 2, 0, WIDTH, HEIGHT);
        explosion = sheet.getImage(WIDTH * 3, 0, WIDTH, HEIGHT);
        playerStaleLeft = sheet.getImage(120, 150,130,150);
        playerStaleRight = sheet.getImage(0, 650,130,150);
        alien = sheet.getImage(510, 207, 104, 143);
        planks = sheet.getImage(600, 0, 150,  150);



        //run left
        playerRunLeft = new BufferedImage[6];
        playerRunLeft[0] = sheet.getImage(360, 320,130,150);
        playerRunLeft[1] = sheet.getImage(170, 480,130,150);
        playerRunLeft[2] = sheet.getImage(0, 480,130,150);
        playerRunLeft[3] = sheet.getImage(160, 320,130,150);
        playerRunLeft[4] = sheet.getImage(360, 480,130,150);
        playerRunLeft[5] = sheet.getImage(0, 320,130,150);

        //run right
        playerRunRight = new BufferedImage[6];
        playerRunRight[0] = sheet.getImage(0, 820,130,150);
        playerRunRight[1] = sheet.getImage(210, 980,130,150);
        playerRunRight[2] = sheet.getImage(370, 980,130,150);
        playerRunRight[3] = sheet.getImage(0, 980,130,150);
        playerRunRight[4] = sheet.getImage(210, 820,130,150);
        playerRunRight[5] = sheet.getImage(370, 820,130,150);
    }


}
