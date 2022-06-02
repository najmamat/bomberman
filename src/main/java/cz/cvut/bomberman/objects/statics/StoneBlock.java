package cz.cvut.bomberman.objects.statics;

import cz.cvut.bomberman.game.Assets;

public class StoneBlock extends Block{

    public StoneBlock(int id) {
        super(Assets.stone, id);
    }

    @Override
    public boolean isWall(){
        return false;
    }
}