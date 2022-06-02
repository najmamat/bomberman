package cz.cvut.bomberman.objects;

import cz.cvut.bomberman.game.Assets;
import cz.cvut.bomberman.game.Handler;
import cz.cvut.bomberman.objects.statics.Block;
import cz.cvut.bomberman.objects.statics.Bomb;
import cz.cvut.bomberman.utils.Animation;

import java.awt.*;

public class Player extends MainObject{
    private Bomb bomb;
    public boolean isAlive = true;
    private int headDirection = 3; // For sprite rendering (1 = left, 2 = right)

    private int fireLength;
    private int bombsMaxNum = 3; // Maximum number of bombs
    private int bombsAvailable = bombsMaxNum; // Bombs available to place
    private int bombsPlaced = 0; // Number of bombes placed on the map

    public static int bombsPlacedStat = 0;
    public static int totalDamageDealt = 0;

    //private boolean kick; // Allow kicking bombs

    protected float speed;
    public static final float DEFAULT_SPEED = 3.0f;
    public static final int DEFAULT_PLAYER_WIDTH = 74;
    public static final int DEFAULT_PLAYER_HEIGHT = 74;
    public static final int DEFAULT_HEALTH = 1;

    private int upMoveKey;
    private int downMoveKey;
    private int leftMoveKey;
    private int rightMoveKey;
    public int bombPlaceKey;

    protected int health;
    protected float xMove, yMove;
    private String lastKeyPressed = "";
    private static Handler handler;

    private String name;

    public float getxMove() {
        return xMove;
    }


    //Gettery a Settery

    public void setxMove(float xMove) {
        this.xMove = xMove;
    }

    public float getyMove() {
        return yMove;
    }

    public void setyMove(float yMove) {
        this.yMove = yMove;
    }

    protected boolean movingRight;

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getBombsMaxNum() {
        return bombsMaxNum;
    }

    public void setBombsMaxNum(int bombsMaxNum) {
        this.bombsMaxNum = bombsMaxNum;
    }

    public int getBombsAvailable() {
        return bombsAvailable;
    }

    public void setBombsAvailable(int bombsAvailable) {
        this.bombsAvailable = bombsAvailable;
    }

    public int getBombsPlaced() {
        return bombsPlaced;
    }

    public void setBombsPlaced(int bombsPlaced) {
        this.bombsPlaced = bombsPlaced;
    }

    public String getName() {
        return name;
    }

    // konec Getteru a Setteru

    // Animace
    private Animation animationRunLeft;
    private Animation animationRunRight;

    /**
     * Class for our Player. In constructor it gets a Handler, coordinates, keyboard controls for this player his name and health.
     * In constructor Player also creates his collisionRectangle. It is a bit smaller, to add depth to our game.
     * @param handler
     * @param x
     * @param y
     * @param upMoveKey
     * @param downMoveKey
     * @param leftMoveKey
     * @param rightMoveKey
     * @param bombPlaceKey
     * @param name
     * @param playerHealth
     */
    public Player(Handler handler, float x, float y, int upMoveKey, int downMoveKey, int leftMoveKey, int rightMoveKey, int bombPlaceKey, String name, int playerHealth) {
        super(x, y, Player.DEFAULT_PLAYER_WIDTH, Player.DEFAULT_PLAYER_HEIGHT);
        this.handler = handler;
        this.name = name;
        health = DEFAULT_HEALTH+ playerHealth;
        speed = DEFAULT_SPEED;

        this.upMoveKey = upMoveKey;
        this.downMoveKey = downMoveKey;
        this.rightMoveKey = rightMoveKey;
        this.leftMoveKey = leftMoveKey;
        this.bombPlaceKey = bombPlaceKey;

        collisionRec.x = 28;
        collisionRec.y = 35;
        collisionRec.width = 32;
        collisionRec.height = 35;

        animationRunLeft = new Animation(100, Assets.playerRunLeft);
        animationRunRight = new Animation(100, Assets.playerRunRight);
    }

    /**
     * Method to move Player across the map. Call 2 other methods to with collision detection inside them.
     */
    public void move(){
        moveX();
        moveY();
    }

    /**
     * Method moveX is used to change players coordinate X. It also contains collision detection to check, if a player can move
     * to the location i tell it to.
     */
    public void moveX(){
        if (xMove > 0){ //pohyb doprava
            int tmpX = (int) (x + xMove + collisionRec.x + collisionRec.width) / Block.BLOCK_WIDTH;
            if (!blockCollision(tmpX, (int) (y + collisionRec.y) / Block.BLOCK_HEIGHT) &&
                    !blockCollision(tmpX, (int) (y + collisionRec.y + collisionRec.height) / Block.BLOCK_WIDTH)){
                // pokud tam neni solid block, pohni se
                x += xMove;
            }
        } else if (xMove < 0){ //pohyb doleva
            int tmpX = (int) (x + xMove + collisionRec.x) / Block.BLOCK_WIDTH;
            if (!blockCollision(tmpX, (int) (y + collisionRec.y) / Block.BLOCK_HEIGHT) &&
                    !blockCollision(tmpX, (int) (y + collisionRec.y + collisionRec.height) / Block.BLOCK_HEIGHT)){
                // pokud tam neni solid block, pohni se
                x += xMove;
            }
        }
    }

    /**
     * Method moveY is the same as moveX, but for Y coordinate. It also contains collision detection.
     */
    public void moveY(){
        if (yMove < 0){ //pohyb nahoru
            int tmpY = (int) (y + yMove + collisionRec.y) / Block.BLOCK_HEIGHT;
            if (!blockCollision((int) (x + collisionRec.x) / Block.BLOCK_WIDTH, tmpY) &&
                    !blockCollision((int) (x + collisionRec.x + collisionRec.width) / Block.BLOCK_WIDTH, tmpY)){
                // pokud tam neni solid block, pohni se
                y += yMove;
            }
        } else if (yMove > 0){ //pohyb dolu
            int tmpY = (int) (y + yMove + collisionRec.y + collisionRec.height) / Block.BLOCK_HEIGHT;
            if (!blockCollision((int) (x + collisionRec.x) / Block.BLOCK_WIDTH, tmpY) &&
                    !blockCollision((int) (x + collisionRec.x + collisionRec.width) / Block.BLOCK_WIDTH, tmpY)){
                // pokud tam neni solid block, pohni se
                y += yMove;
            }
        }
    }

    /**
     * Easy method to check, if there is a solid block in direction where player wants to move.
     * @param x
     * @param y
     * @return
     */
    public boolean blockCollision(int x, int y){
        return handler.getMap().getBlock(x, y).isWall();
    }

    @Override
    public void update() {
        //Animace
        animationRunLeft.update();
        animationRunRight.update();

        getInput();
        move();
    }

    /**
     * This method getInput gets updated 60 times per second. It gets user input and updates data. Currently it detects movement
     * and then tries to move a player.
     */
    private void getInput(){
        xMove = 0;
        yMove = 0;

        if (handler.getPlayerControls().getK(upMoveKey)){
            yMove = -speed;
            headDirection = 2;
            lastKeyPressed = "up";
        }
        if (handler.getPlayerControls().getK(downMoveKey)){
            yMove = speed;
            headDirection = 1;
            lastKeyPressed = "down";
        }
        if (handler.getPlayerControls().getK(leftMoveKey)){
            xMove = -speed;
            headDirection = 1;
            lastKeyPressed = "left";
        }
        if (handler.getPlayerControls().getK(rightMoveKey)){
            xMove = speed;
            headDirection = 2;
            lastKeyPressed = "right";
        }

        if (handler.getPlayerControls().getK(upMoveKey) == false && lastKeyPressed == "up"){
            headDirection = 4;
        }
        if (handler.getPlayerControls().getK(downMoveKey) == false && lastKeyPressed == "down"){
            headDirection = 3;
        }

        if (handler.getPlayerControls().getK(leftMoveKey) == false && lastKeyPressed == "left"){
            headDirection = 3;
        }
        if (handler.getPlayerControls().getK(rightMoveKey) == false && lastKeyPressed == "right"){
            headDirection = 4;
        }
    }

    /**
     * Class for rendering Player. Utilizes a list of BufferedImages in assets to animate a Player.
     * @param graphics
     */
    @Override
    public void render(Graphics graphics) {
        // texture
        if (isAlive){
            if (headDirection == 3){
                graphics.drawImage(Assets.playerStaleLeft, (int) x, (int) y, width, height, null);
                /*graphics.setColor(Color.red);
                graphics.fillRect(collisionRec.x + (int) x, collisionRec.y + (int) y, collisionRec.width, collisionRec.height);*/
            }else if (headDirection == 4){
                graphics.drawImage(Assets.playerStaleRight, (int) x, (int) y, width, height, null);
                /*graphics.setColor(Color.red);
                graphics.fillRect(collisionRec.x + (int) x, collisionRec.y + (int) y, collisionRec.width, collisionRec.height);*/
            } else if (headDirection == 1){
                graphics.drawImage(animationRunLeft.getCurrImage(), (int) x, (int) y, width, height, null);
                /*graphics.setColor(Color.red);
                graphics.fillRect(collisionRec.x + (int) x, collisionRec.y + (int) y, collisionRec.width, collisionRec.height);*/
            } else if (headDirection == 2){
                graphics.drawImage(animationRunRight.getCurrImage(), (int) x, (int) y, width, height, null);
                /*graphics.setColor(Color.red);
                graphics.fillRect(collisionRec.x + (int) x, collisionRec.y + (int) y, collisionRec.width, collisionRec.height);*/
            }
        }
    }
}
