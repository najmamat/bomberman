package cz.cvut.bomberman.objects.statics;

import cz.cvut.bomberman.game.Assets;

public class BrickBlock extends Block{

    public BrickBlock(int id) {
        super(Assets.brick, id);
    }

    @Override
    public boolean isWall(){
        return true;
    }
}
