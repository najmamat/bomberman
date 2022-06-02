package cz.cvut.bomberman.objects.statics;

import cz.cvut.bomberman.game.Assets;
import cz.cvut.bomberman.game.Handler;
import cz.cvut.bomberman.game.client.EndGame;
import cz.cvut.bomberman.game.map.GameMap;
import cz.cvut.bomberman.objects.Player;
import cz.cvut.bomberman.utils.BombTimer;

import java.awt.*;
import java.util.ArrayList;

import static cz.cvut.bomberman.objects.Player.bombsPlacedStat;
import static cz.cvut.bomberman.objects.Player.totalDamageDealt;


public class Bomb extends StaticObject{
    public Player owner;
    private Handler handler;
    private GameMap map;
    private Bomb nextBomb;
    private BombTimer timer;
    public ArrayList<Player> hitPlayers = new ArrayList<Player>();

    private float explodeTime = 3;
    public boolean exploded = false;

    public boolean placeBomb = false;
    private ArrayList<Player> allPlayers;

    private int playersAlive = 0;

    /**
     * Class Bomb is a static object.
     * @param handler
     * @param x
     * @param y
     * @param owner
     * @param allPlayers
     *
     * <p>It gets a list of all players for explosions and checking for winner of the game</p>
     */
    public Bomb(Handler handler, float x, float y, Player owner, ArrayList<Player> allPlayers) {
        super(x, y, DEFAULT_OBJECT_WIDTH + 20, DEFAULT_OBJECT_HEIGHT + 20);
        this.handler = handler;
        this.owner = owner;
        this.allPlayers = allPlayers;
        collisionRec = new Rectangle(0, 0, 75, 75);
    }

    @Override
    public void update(){
        if (owner.isAlive){
            for (Player p : allPlayers){
                getInput();
                exploCollision(p);
            }
        }
    }

    /**
     * Method getInput is updated 60 times per second. It waits for a signal from a user keyboard to place a bomb on the map
     * and starts a timer.
     */
    private void getInput(){

        if (handler.getPlayerControls().getK(owner.bombPlaceKey)) {
            if (owner.getBombsPlaced() == 0 && exploded == false) {
                owner.setBombsPlaced(owner.getBombsPlaced() + 1);
                bombsPlacedStat += 1;
                hitPlayers.clear();
                owner.setBombsAvailable(owner.getBombsAvailable() - 1);
                placeBomb = true;
                timer = new BombTimer((int) explodeTime, this);
                setX(owner.getX());
                setY(owner.getY());
            }
        }
    }


    @Override
    public void render(Graphics graphics) {

        if (placeBomb == true) {
            graphics.drawImage(Assets.bomb, (int) x, (int) y, width, height, null);
            //graphics.setColor(Color.red);
            //graphics.fillRect(collisionRec.x + (int) x, collisionRec.y + (int) y, collisionRec.width, collisionRec.height);
        }
        if (exploded == true) {
            graphics.drawImage(Assets.explosion, (int) x, (int) y, width, height, null);
        }
    }

    /**
     * Method exploCollision is used to find out if an exploded bomb has hit a player. It gets a Player in the parametr,
     * because it checks one player at a time.
     * @param player
     */
    private void exploCollision(Player player){
        if (exploded == true){
            if (hitPlayers.isEmpty()){
                if (isCollided(player.collisionRec, this.collisionRec, player)) {
                    player.setHealth(player.getHealth() - 1);
                    totalDamageDealt += 1;

                    System.out.println(player.getHealth());

                    //add hit player to hitPlayers list
                    hitPlayers.add(player);

                    if (player.getHealth() == 0) {
                        player.isAlive = false;
                        checkForWinner();
                    }
                }
            }
            if (hitPlayers.contains(player) == false){
                if (isCollided(player.collisionRec, this.collisionRec, player)){
                    player.setHealth(player.getHealth() - 1);
                    totalDamageDealt += 1;

                    System.out.println(player.getHealth());

                    //add hit player to hitPlayers list
                    hitPlayers.add(player);

                    if (player.getHealth() == 0){
                        player.isAlive = false;
                        checkForWinner();
                    }
                }
            }
        }
    }

    /**
     * Method isCollided is for checking is a bombs hitbox has collided with players hitbox.
     * @param p
     * @param b
     * @param player
     * @return
     */
    public boolean isCollided(Rectangle p, Rectangle b, Player player){
        if (
                x + b.x < player.getX()+ p.x
                && x + b.x + b.width > player.getX() + p.x
                && y + b.y < player.getY() + p.y
                && y + b.y + b.height > player.getY() + p.y
        ) {
            System.out.println("1");
            return true;
        } else if (
                x + b.x + b.width > player.getX() + p.x + p.width
                && x + b.x < player.getX() + p.x + p.width
                && y + b.y < player.getY() + p.y
                && y + b.y + b.height > player.getY() + p.y) {
            System.out.println("2");
            return true;
        } else if (
                x + b.x < player.getX()+ p.x
                && x + b.x + b.width > player.getX() + p.x
                && y + b.y < player.getY() + p.y + p.height
                && y + b.y + b.height > player.getY() + p.y + p.height) {
            System.out.println("3");
            return true;
        } else if (
                x + b.x < player.getX()+ p.x + p.width
                && x + b.x + b.width > player.getX() + p.x + p.width
                && y + b.y < player.getY() + p.y + p.height
                && y + b.y + b.height > player.getY() + p.y + p.height
        ) {
            System.out.println("4");
            return true;
        }else {
            return false;
        }
    }

    /**
     * Method checkForWinner is for determining the winner and the end of the game.
     */
    private void checkForWinner(){
        for (Player p : allPlayers){
            if (p.getHealth() > 0){
                playersAlive += 1;
            }
        }
        if (playersAlive == 1){
            for (Player p : allPlayers){
                if (p.getHealth() > 0){
                    new EndGame(1200, 950, bombsPlacedStat, p.getName(), totalDamageDealt);
                    handler.getGame().stop();
                }
            }
        }
        playersAlive = 0;
    }

/*    private void exploCollisionPlanks(Player player){
        if (exploded == true){
            if (isCollided(planksBlock.collisionRec, this.collisionRec, player)){
                //System.out.println("exploded planks");
                planksBlock.changeTexture(Assets.stone);
                planksBlock.changeWall(false);
            }
        }
    }*/
}
