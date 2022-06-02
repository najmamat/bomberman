package cz.cvut.bomberman.utils;

import cz.cvut.bomberman.objects.statics.Bomb;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class BombTimer is used to setup a Timer when a Bomb is placed.
 */
public class BombTimer {
    private Timer timer;
    private Bomb bomb;

    public BombTimer(int seconds, Bomb bomb){
        this.bomb = bomb;
        timer = new Timer();
        timer.schedule(new EndOfTimer(), seconds*1000);
    }

    /**
     * Class EndOfTimer says what will happend, when the timer ends. Our Bomb timer creates another Timer called
     * ExplosionTimer.
     */
    class EndOfTimer extends TimerTask {
        public void run(){
            bomb.placeBomb = false;
            bomb.owner.setBombsPlaced(bomb.owner.getBombsPlaced() - 1);
            //System.out.println(bomb.owner.getBombsPlaced());
            bomb.exploded = true;
            new ExplosionTimer(1, bomb);
            timer.cancel();
        }
    }
}
