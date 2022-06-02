package cz.cvut.bomberman.objects;

import javax.net.ssl.KeyManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class KeyboardControls implements KeyListener so our game has input from the users keyboard.
 */
public class KeyboardControls implements KeyListener {

    private boolean[] keys;
   // public boolean up, down, left, right, placeBombP1, placeBombP2, w, a , s, d;

    public KeyboardControls(){
        keys = new boolean[256];
    }

    public void tick(){
/*        up = keys[38];
        down = keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_RIGHT];
        placeBombP1 = keys[KeyEvent.VK_SHIFT];
        w = keys[KeyEvent.VK_W];
        a = keys[KeyEvent.VK_A];
        s = keys[KeyEvent.VK_S];
        d = keys[KeyEvent.VK_D];
        placeBombP2 = keys[KeyEvent.VK_UP];*/
    }

    public boolean getK(int k){
        return keys[k];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }


}