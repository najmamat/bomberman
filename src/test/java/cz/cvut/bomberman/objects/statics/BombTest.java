package cz.cvut.bomberman.objects.statics;

import cz.cvut.bomberman.game.Game;
import cz.cvut.bomberman.game.Handler;
import cz.cvut.bomberman.objects.Player;
import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BombTest {
    private ArrayList<Player> allPlayers;
    private Game game = new Game("Bomberman", 300, 300,"resources/maps/map1.txt", 2, 2);
    private Handler handler = new Handler(game);
    private Player owner = new Player(handler, 0, 0, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SHIFT, "Player 1", 2);

    @Test
    public void rectangle_collision_r1_fullInside_r2(){
        //arrange
        owner.setX(10);
        owner.setY(10);
        Bomb bomb = new Bomb(handler, 0,0, owner, allPlayers);
        boolean expectedResult = true;

        //act
        boolean result = bomb.isCollided(owner.collisionRec, bomb.collisionRec, owner);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void rectangle_collision_r1_2_bottom_edges_in_r2(){
        //arrange
        Bomb bomb = new Bomb(handler, 0,50, owner, allPlayers);
        boolean expectedResult = true;

        //act
        boolean result = bomb.isCollided(owner.collisionRec, bomb.collisionRec, owner);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void rectangle_collision_r1_2_left_edges_in_r2(){
        //arrange
        owner.setX(45);
        Bomb bomb = new Bomb(handler, 0,0, owner, allPlayers);
        boolean expectedResult = true;

        //act
        boolean result = bomb.isCollided(owner.collisionRec, bomb.collisionRec, owner);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void rectangle_collision_r1_2_top_edges_in_r2(){
        //arrange
        owner.setX(10);
        owner.setY(39);
        Bomb bomb = new Bomb(handler, 0,0, owner, allPlayers);
        boolean expectedResult = true;

        //act
        boolean result = bomb.isCollided(owner.collisionRec, bomb.collisionRec, owner);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void rectangle_collision_r1_2_right_edges_in_r2(){
        //arrange
        owner.setY(15);
        Bomb bomb = new Bomb(handler, 59,0, owner, allPlayers);
        boolean expectedResult = true;

        //act
        boolean result = bomb.isCollided(owner.collisionRec, bomb.collisionRec, owner);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void rectangle_collision_r1_left_bottom_edge_in_r2(){
        //arrange
        owner.setX(46);
        Bomb bomb = new Bomb(handler, 0,10, owner, allPlayers);
        boolean expectedResult = true;

        //act
        boolean result = bomb.isCollided(owner.collisionRec, bomb.collisionRec, owner);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void rectangle_collision_r1_left_top_edge_in_r2(){
        //arrange
        owner.setX(46);
        owner.setY(39);
        Bomb bomb = new Bomb(handler, 0,0, owner, allPlayers);
        boolean expectedResult = true;

        //act
        boolean result = bomb.isCollided(owner.collisionRec, bomb.collisionRec, owner);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void rectangle_collision_r1_right_top_edge_in_r2(){
        //arrange
        owner.setY(39);
        Bomb bomb = new Bomb(handler, 59,0, owner, allPlayers);
        boolean expectedResult = true;

        //act
        boolean result = bomb.isCollided(owner.collisionRec, bomb.collisionRec, owner);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void rectangle_collision_r1_right_bottom_edge_in_r2(){
        //arrange
        Bomb bomb = new Bomb(handler, 59,69, owner, allPlayers);
        boolean expectedResult = true;

        //act
        boolean result = bomb.isCollided(owner.collisionRec, bomb.collisionRec, owner);

        //assert
        assertEquals(expectedResult, result);
    }

    @Test
    public void rectangle_collision_r1_outside_r2(){
        //arrange
        owner.setX(47);
        Bomb bomb = new Bomb(handler, 0,0, owner, allPlayers);
        boolean expectedResult = false;

        //act
        boolean result = bomb.isCollided(owner.collisionRec, bomb.collisionRec, owner);

        //assert
        assertEquals(expectedResult, result);
    }
}
