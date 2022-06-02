package cz.cvut.bomberman.utils;

import cz.cvut.bomberman.game.Handler;
import cz.cvut.bomberman.objects.statics.Bomb;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Class Explosion Timer sets up a Timer for Explosion.
 */
public class ExplosionTimer {
    private static Handler handler;
    private Timer timer;
    private Bomb bomb;

    public ExplosionTimer(int seconds, Bomb bomb){
        this.bomb = bomb;
        timer = new Timer();
        timer.schedule(new EndOfTimer(), seconds*1000);
    }

    class EndOfTimer extends TimerTask {
        public void run(){
            bomb.exploded = false;
            timer.cancel();
        }
    }
}
