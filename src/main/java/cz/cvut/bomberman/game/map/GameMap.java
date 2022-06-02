package cz.cvut.bomberman.game.map;

import cz.cvut.bomberman.game.Handler;
import cz.cvut.bomberman.objects.ObjectManager;
import cz.cvut.bomberman.objects.Player;
import cz.cvut.bomberman.objects.statics.Block;
import cz.cvut.bomberman.objects.statics.Bomb;
import cz.cvut.bomberman.objects.statics.PlanksBlock;
import cz.cvut.bomberman.utils.Utilities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Class GameMap is for loading up map from txt file and creating our objects and players.
 * In constructor it gets a Handler(class for handling data), String path to our map.txt file, int numberOfPlayers in the game
 * and int playersHealth.
 * Our txt file format looks like this:
 *
 * 16 11
 * 100 200
 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 * 0 1 1 1 1 1 1 1 1 0 1 0 1 1 1 0
 * 0 1 1 0 0 0 0 0 1 1 1 1 1 0 1 0
 * 0 1 1 0 1 1 1 0 1 0 0 0 0 0 1 0
 * 0 2 0 0 1 0 1 0 1 1 1 1 1 1 1 0
 * 0 2 1 1 1 0 2 0 1 0 0 1 0 1 1 0
 * 0 1 0 0 0 0 1 2 1 1 1 1 0 1 0 0
 * 0 1 1 1 1 0 1 1 1 0 1 0 0 1 0 0
 * 0 1 0 0 1 0 1 1 1 0 1 1 1 1 0 0
 * 0 0 1 1 1 0 0 1 0 1 1 0 1 0 1 0
 * 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 *
 * <ul>First 2 numbers are dimension of our map. Width and Height.</ul>
 * <ul>Second 2 numbers are spawn coordinates of players.</ul>
 * <ul>Rectangle of numbers has to be exactly like said dimensions and numbers. Numbers mean different kind of blocks</ul>
 * <li> 0 : brick block (is a wall)</li>
 * <li> 1 : stone block (is walkable)</li>
 * <li> 2 : plank block (is a wall)</li>
 *
 * <p>This class creates all players, players bombs a loads a map. Then it uses ObjectManage, that is responsible for
 * updating and rendering of objects, that are in our game.</p>
 */
public class GameMap {

    private int width, height;
    private int xSpawn, ySpawn;
    private int[][] blocks;

    private ObjectManager objectManager;
    private String path;
    private Handler handler;
    private int numberOfPlayers;

    public GameMap (Handler handler, String path, int numberOfPlayers, int playerHealth){
        this.numberOfPlayers = numberOfPlayers;
        this.handler = handler;
        this.path = path;
        loadMap(path); //loads up the map

        //create players and add them to the objectManager
        ArrayList<Player> playerArrayList= new ArrayList<Player>();
        for (int i = 0; i < numberOfPlayers; i++) {
            if (i == 0) {
                Player player1 = new Player(handler, xSpawn, ySpawn, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SHIFT, "Player 1", playerHealth);
                playerArrayList.add(player1);
            } else if (i == 1){
                Player player2 = new Player(handler, xSpawn, ySpawn, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_Q, "Player 2", playerHealth);
                playerArrayList.add(player2);
            } else if (i == 2){
                Player player3 = new Player(handler, xSpawn, ySpawn, KeyEvent.VK_G, KeyEvent.VK_B, KeyEvent.VK_V, KeyEvent.VK_N, KeyEvent.VK_SPACE, "Player 3", playerHealth);
                playerArrayList.add(player3);
            } else if (i == 3){
                Player player4 = new Player(handler, xSpawn, ySpawn, KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L, KeyEvent.VK_P, "Player 4", playerHealth);
                playerArrayList.add(player4);
            }
        }

        objectManager = new ObjectManager(playerArrayList);

        //creates a bomb for each player
        for (Player p : playerArrayList){
            objectManager.addObject(new Bomb(handler, 0, 0, p, playerArrayList));
        }
    }

    /**
     * This method is made for updating objects inside objectManager. They are rendered afterwards.
     */
    public void update(){
        objectManager.update();
    }

    /**
     * This method is used to call render methods of each object inside objectManager.
     * @param graphics
     */
    public void render(Graphics graphics){
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                getBlock(i, j).render(graphics, i * Block.BLOCK_WIDTH, j * Block.BLOCK_HEIGHT);
            }
        }
        objectManager.render(graphics);
    }

    /**
     * This method getBlock is just a simple getter for our blocks. It also gets coordinates x, y, because it also contains safety feature,
     * that if i would try to get a block outside our map, it still give me something. Same way if i would try to get a Block
     * that is null.
     * @param x
     * @param y
     * @return
     */
    public Block getBlock(int x, int y){
        if(x < 0 || x >= width || y < 0 || y >= height){
            return Block.stoneBlock;
        }

        Block block = Block.blocks[blocks[x][y]];
        if (block == null){
            return Block.stoneBlock;
        }
        return block;
    }

    /**
     * This method loadMap gets a String, that is a path to our map.txt file. It uses methods from Utilities to separate
     * the .txt file and get data from it.
     * @param path
     */
    private void loadMap(String path){
        String file = Utilities.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utilities.parseInt(tokens[0]);
        height = Utilities.parseInt(tokens[1]);
        xSpawn = Utilities.parseInt(tokens[2]);
        ySpawn = Utilities.parseInt(tokens[3]);

        blocks = new int[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                blocks[x][y] = Utilities.parseInt(tokens[(x + y * width) + 4]);
            }
        }
    }
}
