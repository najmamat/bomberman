package cz.cvut.bomberman.objects.statics;

import cz.cvut.bomberman.game.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlanksBlock extends Block{


    //private Rectangle collisionRec;

    public PlanksBlock(int id) {
        super(Assets.planks, id);
        collisionRec = new Rectangle(-20, -20, 115, 115);
    }


}