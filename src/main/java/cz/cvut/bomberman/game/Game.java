package cz.cvut.bomberman.game;


import cz.cvut.bomberman.game.states.GameState;
import cz.cvut.bomberman.objects.KeyboardControls;
import cz.cvut.bomberman.ui.Display;
import cz.cvut.bomberman.utils.State;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;

/**
 * Class Game contains the game itself. It utilizes a Thread to run an instance of the Game. It also has GameLoop implemented inside it.
 * GameLoop is implemented using 3 methods run(), update(), render(). Inside a run() method is a slowdown clock for our game to update and render
 * 60 times per second -> 60 FPS.
 */
public class Game implements Runnable {

    private Display display;
    private int width, height;
    public String title;
    public String path;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bufferStrategy;
    private Graphics graphics;
    private int numberOfPlayers;
    private int playerHealth;

    // States
    public State gameState;

    // Keyboard input
    private KeyboardControls keyboardControls;

    // Handler
    public Handler handler;

    public Game(String title, int width, int height, String path, int numberOfPlayers, int playerHealth) {
        this.height = height;
        this.width = width;
        this.title = title;
        this.path = path;
        this.numberOfPlayers = numberOfPlayers;
        this.playerHealth = playerHealth;
        keyboardControls = new KeyboardControls();
        this.start();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    // Starts a thread
    public synchronized void start() {

        if (running == true) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start(); // zavola run metodu
    }

    // Stops a thread
    public synchronized void stop() {
        display.getJFrame().dispose();
        //ochrana
        if (running == false) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Initializes graphics for our game
    private void init() {
        display = new Display(title, width, height);
        display.getJFrame().addKeyListener(keyboardControls);
        try {
            Assets.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        handler = new Handler(this);

        gameState = new GameState(handler, path, numberOfPlayers, playerHealth);
        //menuState = new MenuState(handler);
        //settingsState = new SettingsState(handler);

        State.setState(gameState);
    }


    // Updates our game
    private void update() {
        keyboardControls.tick();

        if (State.getState() != null) {
            State.getState().update();
        }
    }

    // Uses graphics to render our game
    private void render() {
        bufferStrategy = display.getCanvas().getBufferStrategy();
        if (bufferStrategy == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        graphics = bufferStrategy.getDrawGraphics();
        //window clear
        graphics.clearRect(0, 0, width, height);

        // rendering

        if (State.getState() != null) {
            State.getState().render(graphics);
        }

        bufferStrategy.show();
        graphics.dispose();
    }

    public void run() {
        init();

        int fps = 60;
        double timePerUpdate = 1000000000 / fps;
        double delta = 0;
        long actualTime;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        // GameLoop + GameTimer
        while (running) {
            actualTime = System.nanoTime();
            delta += (actualTime - lastTime) / timePerUpdate;
            timer += actualTime - lastTime;
            lastTime = actualTime;

            if (delta >= 1) {
                update();
                render();
                ticks++;
                delta--;
            }

            if (timer >= 1000000000) {
                //System.out.println("FPS: " + ticks);
                ticks = 0;
                timer = 0;
            }
        }
    }

    public KeyboardControls getPlayerControls() {
        return keyboardControls;
    }

}
